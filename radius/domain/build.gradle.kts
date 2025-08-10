plugins {
    id("java-library")
    id("com.google.devtools.ksp")
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.hilt.core)
    ksp(libs.hilt.android.compiler)
}