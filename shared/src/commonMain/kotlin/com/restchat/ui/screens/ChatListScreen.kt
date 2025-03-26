package com.restchat.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.restchat.model.ChatChannel
import com.restchat.model.ChannelType
import com.restchat.service.ChatService

@Composable
fun ChatListScreen(
    chatService: ChatService,
    onChannelSelected: (String) -> Unit
) {
    var channels by remember { mutableStateOf<List<ChatChannel>>(emptyList()) }
    
    LaunchedEffect(Unit) {
        channels = chatService.getChannels()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("RestChat") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(channels) { channel ->
                ChatChannelItem(
                    channel = channel,
                    onClick = { onChannelSelected(channel.id) }
                )
            }
        }
    }
}

@Composable
private fun ChatChannelItem(
    channel: ChatChannel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = channel.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = channel.lastMessage?.content ?: "No messages yet",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if (channel.unreadCount > 0) {
                Badge(
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(channel.unreadCount.toString())
                }
            }
        }
    }
} 