package com.bestswlkh0310.hertz.core.playlist.res

import com.bestswlkh0310.hertz.core.playlist.domain.Playlist
import com.bestswlkh0310.hertz.core.user.res.UserRes

data class PlaylistRes(
    val id: Int,
    val title: String,
    val thumbnailUrl: String?,
    val user: UserRes
) {
    companion object {
        fun of(playlist: Playlist) = PlaylistRes(
            id = playlist.id,
            title = playlist.title,
            thumbnailUrl = playlist.thumbnailUrl,
            user = UserRes.of(playlist.user)
        )
    }
}
