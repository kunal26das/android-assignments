plugins {
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.jvm)
    id("java-library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation(libs.hilt.core)
    ksp(libs.hilt.android.compiler)
}