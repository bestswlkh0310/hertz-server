package com.bestswlkh0310.hertz.domain.user.model

data class SignUpRequest(
    val username: String,
    val password: String,
    val passwordCheck: String
)