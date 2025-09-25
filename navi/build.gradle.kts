plugins {
    alias(libs.plugins.android.library)
    id("kotlin-parcelize")
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
    id("kotlin-kapt")
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "io.github.kunal26das.navi"
    compileSdk = libs.versions.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.min.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        buildConfig = true
        dataBinding = true
    }
}

dependencies {
    implementation(project(":common"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.gson)
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.coil)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.config)
    implementation(libs.google.android.material)
}