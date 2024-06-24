package com.bestswlkh0310.hertz.core.music.res

import com.bestswlkh0310.hertz.core.music.domain.Music
import com.bestswlkh0310.hertz.core.user.domain.User
import com.bestswlkh0310.hertz.core.user.res.UserRes
import java.time.LocalDateTime

data class MusicRes(
    val id: Int = 0,
    val name: String,
    val description: String,
    val url: String,
    val createdAt: LocalDateTime,
    /**
     * user: Has this music
     */
    val user: UserRes
) {
    companion object {
        fun of(music: Music) = MusicRes(
            id = music.id,
            name = music.name,
            description = music.description,
            url = music.url,
            createdAt = music.createdAt,
            user = UserRes.of(music.user)
        )
    }
}