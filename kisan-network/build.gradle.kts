plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt.android)
    id("kotlin-parcelize")
    alias(libs.plugins.ksp)
    id("kotlin-kapt")
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "io.github.kunal26das.kisan_network"
    compileSdk = 36

    defaultConfig {
        minSdk = 33
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
    implementation(project(":common"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.google.android.material)
}