plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.ksp)
    id("java-library")
}

java {
    sourceCompatibility = CompileOptions.Java
    targetCompatibility = CompileOptions.Java
}

dependencies {
    implementation(libs.gson)
    implementation(libs.hilt.core)
    implementation(libs.retrofit)
    implementation(project(":radius:domain"))
    ksp(libs.hilt.android.compiler)
}