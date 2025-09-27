plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "io.github.kunal26das.epifi"
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
    implementation(libs.androidx.activity)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.room)
    implementation(libs.androidx.room.paging)
    implementation(libs.androidx.room.runtime)
    implementation(libs.coil)
    implementation(libs.converter.gson)
    implementation(libs.firebase.config)
    implementation(libs.google.android.material)
    implementation(libs.gson)
    implementation(libs.hilt.android)
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(platform(libs.firebase.bom))
    implementation(project(":common"))
    kapt(libs.hilt.android.compiler)
    ksp(libs.androidx.room.compiler)
}