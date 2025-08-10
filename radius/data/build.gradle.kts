plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("com.google.devtools.ksp")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":radius:domain"))
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.hilt.core)
    ksp(libs.hilt.android.compiler)
}