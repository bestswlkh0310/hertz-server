package com.bestswlkh0310.hertz.core.spotlike.service

import com.bestswlkh0310.hertz.core.spotlike.SpotLikePort
import com.bestswlkh0310.hertz.core.spotlike.domain.SpotLike
import com.bestswlkh0310.hertz.core.spotlike.res.SpotLikeRes
import com.bestswlkh0310.hertz.core.user.port.GetCurrentUserPort
import com.bestswlkh0310.hertz.core.user.port.UserPort
import com.bestswlkh0310.hertz.infra.exception.CustomException
import com.bestswlkh0310.hertz.infra.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class SpotLikeServiceImpl(
    private val spotLikePort: SpotLikePort,
    private val getCurrentUserPort: GetCurrentUserPort
) : SpotLikeService {
    override fun set(musicId: Int): SpotLikeRes {
        val userId = getCurrentUserPort.getId() ?: throw CustomException(ErrorCode.NOT_FOUND)
        val exists = spotLikePort.exists(userId, musicId)
        return if (exists) {
            val spotLike = spotLikePort.remove(userId)
            SpotLikeRes.of(spotLike)
        } else {
            val spotLike = spotLikePort.create(userId, musicId)
            SpotLikeRes.of(spotLike)
        }
    }
}