plugins {
    kotlin("android")
    id("com.android.application")
    id("org.jetbrains.compose")
}

android {
    namespace = "com.restchat.android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.restchat.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.compose.ui:ui:1.5.4")
} 