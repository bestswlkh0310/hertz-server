package com.bestswlkh0310.hertz.persistence.spotlike

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface SpotLikeRepository : JpaRepository<SpotLikeEntity, Int> {
    fun existsByUserIdAndMusicId(userId: Int, musicId: Int): Boolean

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO SpotLikeEntity(userId, musicId) VALUES (:userId, :musicId)", nativeQuery = true)
    fun insert(userId: Int, musicId: Int): SpotLikeEntity

    fun removeById(id: Int): SpotLikeEntity
}