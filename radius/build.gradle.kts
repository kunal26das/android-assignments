plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "io.github.kunal26das.radius"
    compileSdk = libs.versions.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.min.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.core)
    implementation(libs.androidx.hilt.common)
    implementation(libs.androidx.room)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.startup.runtime)
    implementation(libs.androidx.work.runtime)
    implementation(libs.converter.gson)
    implementation(libs.google.android.material)
    implementation(libs.gson)
    implementation(libs.hilt.android)
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(platform(libs.androidx.compose.bom))
    implementation(project(":common"))
    implementation(project(":radius:domain"))
    ksp(libs.androidx.room.compiler)
    ksp(libs.hilt.android.compiler)
}