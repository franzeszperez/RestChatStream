plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
    id("app.cash.sqldelight")
}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Compose
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                
                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
                
                // Ktor
                implementation("io.ktor:ktor-client-core:2.3.7")
                implementation("io.ktor:ktor-client-content-negotiation:2.3.7")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")
                
                // Stream Chat
                implementation("io.getstream:stream-chat-android-client:5.17.0")
                
                // SQLDelight
                implementation("app.cash.sqldelight:runtime:2.0.1")
                
                // DateTime
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
            }
        }
        
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:2.3.7")
                implementation("app.cash.sqldelight:android-driver:2.0.1")
            }
        }
        
        val iosMain by creating {
            dependencies {
                implementation("io.ktor:ktor-client-darwin:2.3.7")
                implementation("app.cash.sqldelight:native-driver:2.0.1")
            }
        }
    }
}

sqldelight {
    databases {
        create("RestChatDatabase") {
            packageName.set("com.restchat.db")
        }
    }
} 