plugins {
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.jvm)
    id("java-library")
}

java {
    sourceCompatibility = CompileOptions.Java
    targetCompatibility = CompileOptions.Java
}

dependencies {
    implementation(project(":geektrust:domain"))
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.hilt.core)
    ksp(libs.hilt.android.compiler)
}