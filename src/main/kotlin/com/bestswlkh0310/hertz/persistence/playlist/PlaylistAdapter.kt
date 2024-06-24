package com.bestswlkh0310.hertz.persistence.playlist

import com.bestswlkh0310.hertz.core.playlist.domain.Playlist
import com.bestswlkh0310.hertz.core.playlist.port.PlaylistPort
import com.bestswlkh0310.hertz.infra.exception.CustomException
import com.bestswlkh0310.hertz.infra.exception.ErrorCode
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class PlaylistAdapter(
    private val playlistRepository: PlaylistRepository
) : PlaylistPort {
    override fun get(id: Int): Playlist? =
        playlistRepository.findByIdOrNull(id)?.toDomain()

    override fun getAll(id: Int): List<Playlist> =
        playlistRepository.findByUserId(id).map { it.toDomain() }

    override fun create(playlist: Playlist): Playlist {
        val entity = PlaylistEntity.of(playlist)
        return playlistRepository.save(entity).toDomain()
    }

    override fun edit(playlist: Playlist) {
        val oldPlaylist = playlistRepository.findByIdOrNull(playlist.id)?.toDomain()
            ?: throw CustomException(ErrorCode.NOT_FOUND)
        val edittedPlaylist = oldPlaylist.copy(
            title = playlist.title,
            thumbnailUrl = playlist.thumbnailUrl
        )
        playlistRepository.save(PlaylistEntity.of(edittedPlaylist))
    }

    override fun remove(id: Int) =
        playlistRepository.deleteById(id)
}