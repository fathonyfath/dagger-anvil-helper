group = "dev.fathony.anvil-helper"

plugins {
    kotlin("jvm") version "1.8.0"
    id("java-library")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation("com.google.dagger:dagger:2.48")
}
