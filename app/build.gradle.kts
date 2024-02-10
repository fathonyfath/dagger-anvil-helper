import dev.fathony.daggerapply.DaggerApplyPlugin
import dev.fathony.daggerapply.daggerApply

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.squareup.anvil")
}

apply<DaggerApplyPlugin>()

android {
    namespace = "dev.fathony.daggerkspanvilinteraction"
    compileSdk = 33

    defaultConfig {
        applicationId = "dev.fathony.daggerkspanvilinteraction"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.coreKtx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintLayout)
    implementation(libs.google.material)

    implementation(project(":common-scopes"))
    implementation(project(":feature-a-interface"))
    implementation(project(":feature-a"))
    implementation(project(":time-provider-interface"))
    implementation(project(":time-provider"))
    implementation(project(":feature-b-interface"))
    implementation(project(":feature-b"))
    implementation(project(":feature-counter-interface"))
    implementation(project(":feature-counter"))

    implementation(libs.anvilHelper.api)
    anvil(libs.anvilHelper.processor)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}

daggerApply {
    applyProcessor.set(true)
    applyAndroidHelper.set(true)
}
