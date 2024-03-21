package com.bestswlkh0310.hertz.domain.email.api.dto

data class EmailMessage(
    val to: String,
    val subject: String,
    val message: String
)
