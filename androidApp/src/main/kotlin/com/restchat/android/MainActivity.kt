package com.restchat.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.restchat.ui.App
import com.restchat.service.ChatService
import com.restchat.service.StreamChatServiceImpl
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel

class MainActivity : ComponentActivity() {
    private lateinit var chatService: ChatService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Stream Chat client
        val client = ChatClient.Builder(
            apiKey = "bpugv58z3swt", // Replace with your Stream API key
            applicationContext = applicationContext
        ).logLevel(ChatLogLevel.ALL).build()

        chatService = StreamChatServiceImpl(client)

        // Connect test user (replace with actual user authentication)
        client.connectUser(
            userId = "test_user",
            userName = "Test User"
        )

        setContent {
            App(chatService = chatService)
        }
    }
} 