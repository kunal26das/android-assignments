plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "io.github.kunal26das.common"
    compileSdk = libs.versions.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.min.get().toInt()
    }

    compileOptions {
        sourceCompatibility = CompileOptions.Java
        targetCompatibility = CompileOptions.Java
    }
    buildFeatures {
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
    implementation(libs.androidx.core)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.startup.runtime)
    implementation(libs.coil)
    implementation(platform(libs.androidx.compose.bom))
}