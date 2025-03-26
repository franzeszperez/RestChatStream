package com.restchat.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.restchat.model.ChatChannel
import com.restchat.model.Message
import com.restchat.service.ChatService
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun ChatRoomScreen(
    channelId: String,
    chatService: ChatService,
    onNavigateBack: () -> Unit
) {
    var channel by remember { mutableStateOf<ChatChannel?>(null) }
    var messages by remember { mutableStateOf<List<Message>>(emptyList()) }
    var newMessage by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(channelId) {
        channel = chatService.getChannel(channelId)
        messages = chatService.getMessages(channelId)
        
        chatService.observeChannel(channelId).collectLatest { updatedChannel ->
            channel = updatedChannel
            messages = chatService.getMessages(channelId)
            scope.launch {
                listState.animateScrollToItem(messages.size - 1)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(channel?.name ?: "Chat") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                state = listState
            ) {
                items(messages) { message ->
                    MessageItem(message = message)
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = newMessage,
                    onValueChange = { newMessage = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Type a message...") }
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        if (newMessage.isNotBlank()) {
                            chatService.sendMessage(channelId, newMessage)
                            newMessage = ""
                        }
                    }
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Send")
                }
            }
        }
    }
}

@Composable
private fun MessageItem(message: Message) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = message.content,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = message.timestamp.toString(), // TODO: Format timestamp
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
} 