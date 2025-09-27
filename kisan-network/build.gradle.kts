plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "io.github.kunal26das.kisan_network"
    compileSdk = libs.versions.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.min.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.room)
    implementation(libs.androidx.room.runtime)
    implementation(libs.google.android.material)
    implementation(libs.hilt.android)
    implementation(project(":common"))
    ksp(libs.androidx.room.compiler)
    ksp(libs.hilt.android.compiler)
}