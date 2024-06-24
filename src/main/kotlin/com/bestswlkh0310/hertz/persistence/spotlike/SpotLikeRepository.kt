package com.bestswlkh0310.hertz.persistence.spotlike

import org.springframework.data.jpa.repository.JpaRepository

interface SpotLikeRepository : JpaRepository<SpotLikeEntity, Int> {
    fun existsByUserIdAndMusicId(userId: Int, musicId: Int): Boolean
    fun removeByUserIdAndMusicId(userId: Int, musicId: Int): Int
}