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
    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
}