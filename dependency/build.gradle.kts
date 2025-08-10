plugins {
    id("com.android.library")
    id("com.google.devtools.ksp")
    kotlin("android")
}

android {
    namespace = "io.github.kunal26das.dependency"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":radius:data"))
    implementation(libs.hilt.core)
    ksp(libs.hilt.android.compiler)
}