import dev.fathony.daggerapply.DaggerApplyPlugin
import dev.fathony.daggerapply.daggerApply

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

apply<DaggerApplyPlugin>()

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":time-provider-interface"))
}

daggerApply {
    applyScopes.set(true)
}
