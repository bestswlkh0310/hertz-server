package com.bestswlkh0310.hertz.core.music.service

import com.bestswlkh0310.hertz.core.music.domain.Music
import com.bestswlkh0310.hertz.core.music.port.MusicPort
import com.bestswlkh0310.hertz.core.music.req.SaveMusicReq
import com.bestswlkh0310.hertz.core.user.port.GetCurrentUserPort
import com.bestswlkh0310.hertz.core.user.port.UserPort
import com.bestswlkh0310.hertz.infra.exception.CustomException
import com.bestswlkh0310.hertz.infra.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class MusicServiceImpl(
    private val musicPort: MusicPort,
    private val userPort: UserPort,
    private val getCurrentUserPort: GetCurrentUserPort
) : MusicService {
    override fun search(query: String): List<Music> {
        // TODO: Impl
        return listOf()
    }

    override fun save(req: SaveMusicReq): Music {
        val user = getCurrentUserPort.get() ?: throw CustomException(ErrorCode.NOT_FOUND)
        val music = Music(
            name = req.name,
            description = req.description,
            url = req.url,
            user = user
        )
        return musicPort.save(music)
    }

    override fun remove(musicId: Int) {
        TODO("Not yet implemented")
    }
}