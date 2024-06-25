package com.bestswlkh0310.hertz.core.spotlike.service

import com.bestswlkh0310.hertz.core.music.port.MusicPort
import com.bestswlkh0310.hertz.core.spotlike.SpotLikePort
import com.bestswlkh0310.hertz.core.spotlike.domain.SpotLike
import com.bestswlkh0310.hertz.core.spotlike.res.SpotLikeRes
import com.bestswlkh0310.hertz.core.user.port.GetCurrentUserPort
import com.bestswlkh0310.hertz.infra.exception.CustomException
import com.bestswlkh0310.hertz.infra.exception.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class SpotLikeServiceImpl(
    private val spotLikePort: SpotLikePort,
    private val getCurrentUserPort: GetCurrentUserPort,
    private val musicPort: MusicPort
) : SpotLikeService {
    override fun set(musicId: Int): SpotLikeRes {
        val user = getCurrentUserPort.get() ?: throw CustomException(ErrorCode.NOT_FOUND)
        val music = musicPort.get(musicId) ?: throw CustomException(ErrorCode.NOT_FOUND)

        val exists = spotLikePort.exists(user.id, musicId)
        val spotLike = SpotLike(
            id = 0,
            user = user,
            music = music,
            createdAt = LocalDateTime.now()
        )
        return if (exists) {
            spotLikePort.remove(user.id, musicId)
            SpotLikeRes.of(spotLike)
        } else {
            val createdSpotLike = spotLikePort.create(spotLike)
            SpotLikeRes.of(createdSpotLike)
        }
    }
}