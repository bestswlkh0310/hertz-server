package com.bestswlkh0310.hertz.core.music.service

import com.bestswlkh0310.hertz.core.music.domain.Music
import com.bestswlkh0310.hertz.core.music.port.MusicPort
import com.bestswlkh0310.hertz.core.music.req.EditMusicReq
import com.bestswlkh0310.hertz.core.music.req.SaveMusicReq
import com.bestswlkh0310.hertz.core.music.res.MusicRes
import com.bestswlkh0310.hertz.core.user.port.GetCurrentUserPort
import com.bestswlkh0310.hertz.infra.exception.CustomException
import com.bestswlkh0310.hertz.infra.exception.ErrorCode
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MusicServiceImpl(
    private val musicPort: MusicPort,
    private val getCurrentUserPort: GetCurrentUserPort
) : MusicService {
    override fun search(query: String): List<MusicRes> {
        // TODO: impl
        return listOf()
    }

    override fun save(req: SaveMusicReq): MusicRes {
        val user = getCurrentUserPort.get() ?: throw CustomException(ErrorCode.NOT_FOUND)
        val music = Music(
            id = 0,
            name = req.name,
            description = req.description,
            url = req.url,
            user = user,
            createdAt = LocalDateTime.now(),
        )
        val savedMusic = musicPort.save(music)
        return MusicRes.of(savedMusic)
    }

    override fun remove(musicId: Int): String {
        val exists = musicPort.exists(musicId)
        if (!exists) {
            throw CustomException(ErrorCode.NOT_FOUND)
        }
        musicPort.remove(musicId)
        return "success"
    }

    override fun getAll(id: Int): List<MusicRes> {
        val musics = musicPort.getAll(id)
        return musics.map { MusicRes.of(it) }
    }

    override fun editMusic(req: EditMusicReq): MusicRes {
        val music = musicPort.get(req.id) ?: throw CustomException(ErrorCode.NOT_FOUND)
        val userId = getCurrentUserPort.get()?.id ?: throw CustomException(ErrorCode.NOT_FOUND)
        if (music.user.id != userId) {
            throw CustomException(ErrorCode.FORBIDDEN)
        }
        val edittedMusic = music.copy(name = req.name, description = req.description)
        musicPort.save(edittedMusic)
        return MusicRes.of(edittedMusic)
    }
}