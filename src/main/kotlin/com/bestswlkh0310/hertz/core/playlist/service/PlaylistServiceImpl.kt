package com.bestswlkh0310.hertz.core.playlist.service

import com.bestswlkh0310.hertz.core.music.port.MusicPort
import com.bestswlkh0310.hertz.core.playlist.domain.Playlist
import com.bestswlkh0310.hertz.core.playlist.port.PlaylistPort
import com.bestswlkh0310.hertz.core.playlist.req.PlaylistReq
import com.bestswlkh0310.hertz.core.playlist.res.PlaylistRes
import com.bestswlkh0310.hertz.core.user.port.GetCurrentUserPort
import com.bestswlkh0310.hertz.infra.exception.CustomException
import com.bestswlkh0310.hertz.infra.exception.ErrorCode
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PlaylistServiceImpl(
    private val playlistPort: PlaylistPort,
    private val musicPort: MusicPort,
    private val getCurrentUserPort: GetCurrentUserPort
) : PlaylistService {
    override fun getAll(id: Int): List<PlaylistRes> =
        playlistPort.getAll(id).map { PlaylistRes.of(it) }

    override fun create(req: PlaylistReq): PlaylistRes {
        val musics = musicPort.getAll(req.musicIds)
        val user = getCurrentUserPort.get() ?: throw CustomException(ErrorCode.NOT_FOUND)
        val playlist = Playlist(
            title = req.title,
            thumbnailUrl = req.thumbnailUrl,
            musics = musics,
            user = user,
            createdAt = LocalDateTime.now(),
        )
        val createdPlaylist = playlistPort.create(playlist)
        return PlaylistRes.of(createdPlaylist)
    }

    override fun edit(req: PlaylistReq, id: Int): PlaylistRes {
        val playlist = playlistPort.get(id) ?: throw CustomException(ErrorCode.NOT_FOUND)
        val musics = musicPort.getAll(req.musicIds)
        val edittedPlaylist = playlist.copy(
            title = req.title,
            thumbnailUrl = req.thumbnailUrl,
            musics = musics
        )
        playlistPort.edit(edittedPlaylist)
        return PlaylistRes.of(edittedPlaylist)
    }

    override fun remove(id: Int) =
        playlistPort.remove(id)
}