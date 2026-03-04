plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "io.github.kunal26das.alle"
    compileSdk = libs.versions.compile.get().toInt()

    defaultConfig {
        applicationId = "io.github.kunal26das.alle"
        minSdk = libs.versions.min.get().toInt()
        targetSdk = libs.versions.target.get().toInt()
    }

    compileOptions {
        sourceCompatibility = CompileOptions.Java
        targetCompatibility = CompileOptions.Java
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.room.paging)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.work.runtime)
    implementation(libs.coil)
    implementation(libs.google.android.material)
    implementation(libs.kotlinx.coroutines.play.services)
    implementation(libs.text.recognition)
    implementation(platform(libs.androidx.compose.bom))
    implementation(project(":common"))
    ksp(libs.androidx.room.compiler)
}