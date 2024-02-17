import dev.fathony.daggerapply.DaggerApplyPlugin
import dev.fathony.daggerapply.daggerApply

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.squareup.anvil")
}

apply<DaggerApplyPlugin>()

android {
    namespace = "dev.fathony.multiscopebinding"
    compileSdk = 33

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(project(":multiscope-binding-interface"))
    implementation(libs.androidx.coreKtx)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)

    implementation(libs.anvilHelper.api)
    anvil(libs.anvilHelper.processor)
    
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}

daggerApply {
    applyScopes.set(true)
    applyAndroidHelper.set(true)
    applyProcessor.set(true)
    applyCommonScopes.set(true)
} 
