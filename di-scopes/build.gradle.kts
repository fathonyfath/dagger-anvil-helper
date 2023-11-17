import dev.fathony.daggerapply.DaggerApplyPlugin

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

apply<DaggerApplyPlugin>()

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}