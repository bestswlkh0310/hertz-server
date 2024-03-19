package com.bestswlkh0310.hertz.domain.user.model

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)
