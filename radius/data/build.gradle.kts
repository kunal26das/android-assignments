plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.ksp)
    id("java-library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation(libs.gson)
    implementation(libs.hilt.core)
    implementation(libs.retrofit)
    implementation(project(":radius:domain"))
    ksp(libs.hilt.android.compiler)
}