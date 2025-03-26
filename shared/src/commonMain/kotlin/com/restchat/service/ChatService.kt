package com.restchat.service

import com.restchat.model.ChatChannel
import com.restchat.model.Message
import com.restchat.model.User
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.Channel
import io.getstream.chat.android.client.models.Message as StreamMessage
import io.getstream.chat.android.client.models.User as StreamUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ChatService {
    suspend fun connectUser(userId: String, userName: String)
    suspend fun disconnectUser()
    suspend fun getChannels(): List<ChatChannel>
    suspend fun getChannel(channelId: String): ChatChannel?
    suspend fun sendMessage(channelId: String, content: String)
    suspend fun getMessages(channelId: String): List<Message>
    fun observeChannel(channelId: String): Flow<ChatChannel>
}

class StreamChatServiceImpl(
    private val client: ChatClient
) : ChatService {
    override suspend fun connectUser(userId: String, userName: String) {
        val user = StreamUser(
            id = userId,
            name = userName
        )
        client.connectUser(user, client.devToken(userId)).await()
    }

    override suspend fun disconnectUser() {
        client.disconnect()
    }

    override suspend fun getChannels(): List<ChatChannel> {
        return client.queryChannels().await().data.map { it.toChatChannel() }
    }

    override suspend fun getChannel(channelId: String): ChatChannel? {
        return client.queryChannel(channelId).await().data?.toChatChannel()
    }

    override suspend fun sendMessage(channelId: String, content: String) {
        val message = StreamMessage(
            text = content,
            cid = channelId
        )
        client.sendMessage(channelId, message).await()
    }

    override suspend fun getMessages(channelId: String): List<Message> {
        return client.getReplies(channelId).await().data.map { it.toMessage() }
    }

    override fun observeChannel(channelId: String): Flow<ChatChannel> {
        return client.subscribeToChannel(channelId)
            .map { it.toChatChannel() }
    }

    private fun Channel.toChatChannel(): ChatChannel {
        return ChatChannel(
            id = cid,
            name = name ?: "",
            type = if (type == "messaging") ChannelType.DIRECT else ChannelType.GROUP,
            participants = members.map { it.toUser() },
            lastMessage = lastMessage?.toMessage(),
            unreadCount = unreadCount
        )
    }

    private fun StreamMessage.toMessage(): Message {
        return Message(
            id = id,
            senderId = user.id,
            content = text ?: "",
            timestamp = createdAt?.time ?: 0L,
            isRead = read
        )
    }

    private fun StreamUser.toUser(): User {
        return User(
            id = id,
            name = name,
            role = UserRole.STAFF, // Default role, should be updated based on user data
            restaurantId = extraData["restaurantId"] as? String ?: "",
            avatarUrl = image,
            isOnline = online
        )
    }
} 