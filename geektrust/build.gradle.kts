plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "io.github.kunal26das.geektrust"
    compileSdk = libs.versions.compile.get().toInt()

    defaultConfig {
        applicationId = "io.github.kunal26das.geektrust"
        minSdk = libs.versions.min.get().toInt()
        targetSdk = libs.versions.target.get().toInt()
        buildConfigField("String", "BASE_URL", "\"https://findfalcone.geektrust.com\"")
    }

    compileOptions {
        sourceCompatibility = CompileOptions.Java
        targetCompatibility = CompileOptions.Java
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.google.android.material)
    implementation(libs.hilt.android)
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit.result)
    implementation(libs.retrofit)
    implementation(platform(libs.androidx.compose.bom))
    implementation(project(":common"))
    implementation(project(":geektrust:data"))
    implementation(project(":geektrust:domain"))
    ksp(libs.hilt.android.compiler)
}
