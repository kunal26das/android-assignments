plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.ksp)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation(project(":radius:domain"))
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.hilt.core)
    ksp(libs.hilt.android.compiler)
}