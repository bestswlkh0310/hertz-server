package com.bestswlkh0310.hertz.core.spotlike.domain

import com.bestswlkh0310.hertz.core.music.domain.Music
import com.bestswlkh0310.hertz.core.user.domain.User
import java.time.LocalDateTime

data class SpotLike(
    val id: Int = 0,
    val user: User,
    val music: Music,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
