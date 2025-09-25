plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "io.github.kunal26das.dependency"
    compileSdk = libs.versions.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.min.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    implementation(project(":radius:data"))
    implementation(libs.hilt.core)
    ksp(libs.hilt.android.compiler)
}