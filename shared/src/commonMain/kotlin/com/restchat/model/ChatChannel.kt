package com.restchat.model

import kotlinx.serialization.Serializable

@Serializable
data class ChatChannel(
    val id: String,
    val name: String,
    val type: ChannelType,
    val participants: List<User>,
    val lastMessage: Message? = null,
    val unreadCount: Int = 0
)

@Serializable
enum class ChannelType {
    GROUP,
    DIRECT
}

@Serializable
data class Message(
    val id: String,
    val senderId: String,
    val content: String,
    val timestamp: Long,
    val attachments: List<Attachment>? = null,
    val isRead: Boolean = false
)

@Serializable
data class Attachment(
    val id: String,
    val type: AttachmentType,
    val url: String,
    val name: String
)

@Serializable
enum class AttachmentType {
    IMAGE,
    FILE
} 