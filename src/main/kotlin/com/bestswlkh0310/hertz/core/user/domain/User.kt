package com.bestswlkh0310.hertz.core.user.domain

data class User(
    val id: Int = 0,
    val email: String,
    val password: String,
    val role: UserRole
)
