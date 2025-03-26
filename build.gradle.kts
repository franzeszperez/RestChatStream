plugins {
    // Kotlin
    kotlin("multiplatform") version "1.9.22" apply false
    kotlin("android") version "1.9.22" apply false
    kotlin("plugin.serialization") version "1.9.22" apply false
    
    // Android
    id("com.android.application") version "8.2.2" apply false
    id("com.android.library") version "8.2.2" apply false
    
    // Compose
    id("org.jetbrains.compose") version "1.5.11" apply false
    
    // SQLDelight
    id("app.cash.sqldelight") version "2.0.1" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
} 