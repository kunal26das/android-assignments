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
    implementation("com.google.dagger:hilt-core:2.57")
    ksp("com.google.dagger:hilt-android-compiler:2.57")
}