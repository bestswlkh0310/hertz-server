package com.bestswlkh0310.hertz.core.spotlike.res

import com.bestswlkh0310.hertz.core.music.domain.Music
import com.bestswlkh0310.hertz.core.music.res.MusicRes
import com.bestswlkh0310.hertz.core.spotlike.domain.SpotLike
import com.bestswlkh0310.hertz.core.user.res.UserRes
import java.time.LocalDateTime

data class SpotLikeRes(
    val id: Int,
    val user: UserRes,
    val music: MusicRes,
    val createdAt: LocalDateTime
) {
    companion object {
        fun of(spotLike: SpotLike) = SpotLikeRes(
            id = spotLike.id,
            user = UserRes.of(spotLike.user),
            music = MusicRes.of(spotLike.music),
            createdAt = spotLike.createdAt
        )
    }
}