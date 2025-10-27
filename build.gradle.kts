// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Google Services plugin for Firebase
        classpath("com.google.gms:google-services:4.4.0")
    }
}

plugins {
    // Run with:
    // ./gradlew detekt // Simple report in the console
    // ./gradlew detektFormat // To check with enabled auto-correction
    id("ivy.detekt")
    id("com.jraska.module.graph.assertion")

    alias(libs.plugins.gradleWrapperUpgrade)
    alias(libs.plugins.koverPlugin)
}

subprojects {
    apply(plugin = "org.jetbrains.kotlinx.kover")
    kover {
        reports {
            filters {
                excludes {
                    classes(
                        "*Activity",
                        "*Activity\$*",
                        "*.BuildConfig",
                        "dagger.hilt.*",
                        "hilt_aggregated_deps.*",
                        "*.Hilt_*"
                    )
                    annotatedBy("@Composable")
                }
            }
        }
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

wrapperUpgrade {
    gradle {
        create("ivyWallet") {
            repo.set("Ivy-Apps/ivy-wallet")
            baseBranch.set("main")
        }
    }
}
