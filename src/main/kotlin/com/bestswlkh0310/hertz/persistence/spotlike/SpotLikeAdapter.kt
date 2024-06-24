package com.bestswlkh0310.hertz.persistence.spotlike

import com.bestswlkh0310.hertz.core.spotlike.SpotLikePort
import com.bestswlkh0310.hertz.core.spotlike.domain.SpotLike
import org.springframework.stereotype.Component

@Component
class SpotLikeAdapter(
    private val spotLikeRepository: SpotLikeRepository
) : SpotLikePort {
    override fun exists(userId: Int, musicId: Int): Boolean =
        spotLikeRepository.existsByUserIdAndMusicId(userId, musicId)

    override fun create(spotLike: SpotLike): SpotLike =
        spotLikeRepository.save(SpotLikeEntity.of(spotLike)).toDomain()

    override fun remove(userId: Int, musicId: Int) {
        val id = spotLikeRepository.removeByUserIdAndMusicId(userId, musicId)
    }
}