package com.bestswlkh0310.hertz.core.auth.domain

data class Token(
    val accessToken: String,
    val refreshToken: String,
)
