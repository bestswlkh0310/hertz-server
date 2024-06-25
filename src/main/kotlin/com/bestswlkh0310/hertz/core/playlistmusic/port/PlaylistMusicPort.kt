package com.bestswlkh0310.hertz.core.playlistmusic.port

import com.bestswlkh0310.hertz.core.playlistmusic.domain.PlaylistMusic

interface PlaylistMusicPort {
    fun create(playlistMusic: PlaylistMusic)
    fun createAll(playlistMusic: List<PlaylistMusic>)
}