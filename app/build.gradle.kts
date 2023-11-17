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
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation(project(":feature-a-interface"))
    implementation(project(":feature-a"))
    implementation(project(":time-provider-interface"))
    implementation(project(":time-provider"))
    implementation(project(":feature-b-interface"))
    implementation(project(":feature-b"))

    implementation("dev.fathony.anvil-helper:api")
    anvil("dev.fathony.anvil-helper:processor")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

daggerApply {
    applyProcessor.set(true)
    applyAndroidHelper.set(true)
}
