package com.restchat.ui

import androidx.compose.runtime.*
import com.restchat.service.ChatService
import com.restchat.ui.screens.ChatListScreen
import com.restchat.ui.screens.ChatRoomScreen
import com.restchat.ui.theme.RestChatTheme

sealed class Screen {
    object ChatList : Screen()
    data class ChatRoom(val channelId: String) : Screen()
}

@Composable
fun App(
    chatService: ChatService,
    darkTheme: Boolean = false
) {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.ChatList) }

    RestChatTheme(darkTheme = darkTheme) {
        when (currentScreen) {
            is Screen.ChatList -> {
                ChatListScreen(
                    chatService = chatService,
                    onChannelSelected = { channelId ->
                        currentScreen = Screen.ChatRoom(channelId)
                    }
                )
            }
            is Screen.ChatRoom -> {
                val channelId = (currentScreen as Screen.ChatRoom).channelId
                ChatRoomScreen(
                    channelId = channelId,
                    chatService = chatService,
                    onNavigateBack = {
                        currentScreen = Screen.ChatList
                    }
                )
            }
        }
    }
} 