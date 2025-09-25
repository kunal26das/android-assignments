plugins {
    id("java-library")
    id("com.google.devtools.ksp")
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation(libs.hilt.core)
    ksp(libs.hilt.android.compiler)
}