plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    ios()
    
    sourceSets {
        val iosMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
            }
        }
    }
}

compose.experimental {
    web.application {}
} 