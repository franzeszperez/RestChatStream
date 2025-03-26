# RestChat

A cross-platform chat application for restaurant employees built with Kotlin Multiplatform and Compose Multiplatform.

## Prerequisites

- Android Studio Hedgehog (2023.1.1) or newer
- Xcode 15.0 or newer (for iOS development)
- JDK 17 or newer
- Kotlin 1.9.22
- Stream Chat API Key

## Setup

1. Clone the repository
2. Replace `YOUR_STREAM_API_KEY` in the following files with your Stream Chat API key:
   - `androidApp/src/main/kotlin/com/restchat/android/MainActivity.kt`
   - `iosApp/src/iosMain/kotlin/com/restchat/ios/IosApp.kt`

## Running the App

### Android

1. Open the project in Android Studio
2. Wait for the Gradle sync to complete
3. Connect an Android device or start an emulator
4. Click the "Run" button (green play icon) or press Shift+F10

### iOS

1. Open the project in Android Studio
2. Run the following command to generate the Xcode project:
   ```bash
   ./gradlew :iosApp:podspec
   ```
3. Open the generated Xcode project:
   ```bash
   open iosApp/iosApp.xcworkspace
   ```
4. In Xcode:
   - Select your development team in the project settings
   - Set your bundle identifier
   - Connect an iOS device or select a simulator
   - Click the "Run" button (play icon) or press Cmd+R

## Project Structure

- `shared/` - Common code shared between platforms
- `androidApp/` - Android-specific code
- `iosApp/` - iOS-specific code

## Features

- Group chat support
- 1:1 chat support
- Real-time message updates
- Message history
- Unread message count
- User online/offline status 