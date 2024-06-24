package com.bestswlkh0310.hertz.core.playlist.domain

import com.bestswlkh0310.hertz.core.music.domain.Music
import com.bestswlkh0310.hertz.core.user.domain.User

data class Playlist(
    val id: Int = 0,
    val title: String,
    val thumbnailUrl: String?,
    val musics: List<Music>,
    val user: User
)