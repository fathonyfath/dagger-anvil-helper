import dev.fathony.daggerapply.DaggerApplyPlugin
import dev.fathony.daggerapply.daggerApply

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.squareup.anvil")
}

apply<DaggerApplyPlugin>()

android {
    namespace = "dev.fathony.featureb"
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
    implementation(project(":common-scopes"))
    implementation(project(":feature-counter-interface"))
    implementation(project(":viewmodel-factory"))
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.2")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.activity:activity-ktx:1.7.2")
    implementation("com.google.android.material:material:1.9.0")

    implementation("dev.fathony.anvil-helper:api")
    anvil("dev.fathony.anvil-helper:processor")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

daggerApply {
    applyScopes.set(true)
    applyAndroidHelper.set(true)
    applyProcessor.set(true)
}
