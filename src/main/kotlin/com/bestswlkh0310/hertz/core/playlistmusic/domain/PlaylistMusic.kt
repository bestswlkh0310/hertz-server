package com.bestswlkh0310.hertz.core.playlistmusic.domain

import com.bestswlkh0310.hertz.core.music.domain.Music
import com.bestswlkh0310.hertz.core.playlist.domain.Playlist

data class PlaylistMusic(
    val id: Int,
    val music: Music,
    val playlist: Playlist
)