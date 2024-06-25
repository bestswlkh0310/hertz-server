package com.bestswlkh0310.hertz.core.playlist.domain

import com.bestswlkh0310.hertz.core.user.domain.User
import java.time.LocalDateTime

data class Playlist(
    val id: Int,
    val title: String,
    val thumbnailUrl: String?,
    val user: User,
    val createdAt: LocalDateTime
)