plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.services)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "io.github.kunal26das.assignment"
    compileSdk = libs.versions.compile.get().toInt()

    defaultConfig {
        applicationId = "io.github.kunal26das.assignment"
        minSdk = libs.versions.min.get().toInt()
        targetSdk = libs.versions.target.get().toInt()
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        buildConfig = true
        compose = true
        dataBinding = true
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.startup.runtime)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.config)
    implementation(libs.google.android.material)
    implementation(libs.hilt.android)
    implementation(platform(libs.androidx.compose.bom))
    implementation(platform(libs.firebase.bom))
    implementation(project(":common"))
    implementation(project(":cred"))
    implementation(project(":dependency"))
    implementation(project(":epifi"))
    implementation(project(":kisan-network"))
    implementation(project(":kutumb"))
    implementation(project(":navi"))
    implementation(project(":radius"))
    ksp(libs.androidx.hilt.compiler)
    ksp(libs.hilt.android.compiler)
}