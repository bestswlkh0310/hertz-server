package com.bestswlkh0310.hertz.core.user.domain

import java.time.LocalDateTime

data class User(
    val id: Int,
    val email: String,
    val password: String,
    val role: UserRole,
    val createdAt: LocalDateTime
)
