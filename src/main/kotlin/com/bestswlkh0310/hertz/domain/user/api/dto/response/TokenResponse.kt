package com.bestswlkh0310.hertz.domain.user.api.dto.response

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)