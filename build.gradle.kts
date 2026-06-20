plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.firebase.crashlytics).apply(false)
    alias(libs.plugins.google.services).apply(false)
    alias(libs.plugins.hilt.android).apply(false)
    alias(libs.plugins.kotlin.compose).apply(false)
    alias(libs.plugins.kotlin.jvm).apply(false)
    alias(libs.plugins.kotlin.ksp).apply(false)
}

val kotlinVersion = libs.versions.kotlin.get()

allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    configurations.all {
        resolutionStrategy {
            force("org.jetbrains.kotlin:kotlin-metadata-jvm:$kotlinVersion")
        }
    }
}