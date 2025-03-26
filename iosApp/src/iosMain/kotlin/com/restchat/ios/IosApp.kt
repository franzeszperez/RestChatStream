package com.restchat.ios

import com.restchat.service.ChatService
import com.restchat.service.StreamChatServiceImpl
import com.restchat.ui.App
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import platform.Foundation.NSBundle
import platform.UIKit.UIApplication
import platform.UIKit.UIWindow
import platform.UIKit.UIWindowScene
import platform.UIKit.UIWindowSceneDelegate
import platform.UIKit.setValue

class IosApp {
    private lateinit var window: UIWindow
    private lateinit var chatService: ChatService

    fun onCreate(application: UIApplication, windowScene: UIWindowScene) {
        // Initialize Stream Chat client
        val client = ChatClient.Builder(
            apiKey = "YOUR_STREAM_API_KEY", // Replace with your Stream API key
            applicationContext = NSBundle.mainBundle
        ).logLevel(ChatLogLevel.ALL).build()

        chatService = StreamChatServiceImpl(client)

        // Connect test user (replace with actual user authentication)
        client.connectUser(
            userId = "test_user",
            userName = "Test User"
        )

        // Create and configure the window
        window = UIWindow(windowScene)
        window.setValue(true, "userInteractionEnabled")
        
        // Set up the root view controller with our Compose UI
        window.rootViewController = App(chatService = chatService)
        window.makeKeyAndVisible()
    }
} 