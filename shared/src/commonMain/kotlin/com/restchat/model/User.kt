package com.restchat.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val role: UserRole,
    val restaurantId: String,
    val avatarUrl: String? = null,
    val isOnline: Boolean = false
)

@Serializable
enum class UserRole {
    ADMIN,
    MANAGER,
    STAFF
} 