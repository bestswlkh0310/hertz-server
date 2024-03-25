package com.bestswlkh0310.hertz.domain.musicplaylist.core.repository

import com.bestswlkh0310.hertz.domain.music.core.model.MusicEntity
import com.bestswlkh0310.hertz.domain.musicplaylist.core.model.MusicPlaylistEntity
import com.bestswlkh0310.hertz.domain.playlist.core.model.PlaylistEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MusicPlaylistRepository: CrudRepository<MusicPlaylistEntity, Int> {

    fun existsByMusicAndPlaylist(musicEntity: MusicEntity, playlistEntity: PlaylistEntity): Boolean

    fun findByMusicAndPlaylist(musicEntity: MusicEntity, playlistEntity: PlaylistEntity): MusicPlaylistEntity

}