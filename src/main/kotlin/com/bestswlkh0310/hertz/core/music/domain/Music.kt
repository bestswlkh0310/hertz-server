package com.bestswlkh0310.hertz.core.music.domain

import com.bestswlkh0310.hertz.core.user.domain.User
import java.time.LocalDateTime

data class Music(
    val id: Int = 0,
    val name: String,
    val description: String,
    val url: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    /**
     * user: Has this music
     */
    val user: User
)
