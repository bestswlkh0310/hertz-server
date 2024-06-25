package com.bestswlkh0310.hertz.persistence.playlistmusic

import com.bestswlkh0310.hertz.core.playlistmusic.domain.PlaylistMusic
import com.bestswlkh0310.hertz.core.playlistmusic.port.PlaylistMusicPort
import org.springframework.stereotype.Component

@Component
class PlaylistMusicAdapter(
    private val playlistMusicRepository: PlaylistMusicRepository
) : PlaylistMusicPort {
    override fun create(playlistMusic: PlaylistMusic) {
        playlistMusicRepository.save(PlaylistMusicEntity.of(playlistMusic))
    }

    override fun createAll(playlistMusic: List<PlaylistMusic>) {
        playlistMusicRepository.saveAll(playlistMusic.map { PlaylistMusicEntity.of(it) })
    }
}