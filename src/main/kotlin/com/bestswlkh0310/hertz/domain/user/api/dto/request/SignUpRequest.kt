package com.bestswlkh0310.hertz.domain.user.api.dto.request

data class SignUpRequest(
    val username: String,
    val password: String,
    val passwordCheck: String,
    val code: String
)