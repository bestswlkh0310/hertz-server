package com.bestswlkh0310.hertz.persistence.playlist

import org.springframework.data.jpa.repository.JpaRepository

interface PlaylistRepository : JpaRepository<PlaylistEntity, Int> {
    fun findByUserId(userId: Int): List<PlaylistEntity>
}