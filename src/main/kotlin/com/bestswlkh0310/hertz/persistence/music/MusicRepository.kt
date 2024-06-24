package com.bestswlkh0310.hertz.persistence.music

import org.springframework.data.jpa.repository.JpaRepository

interface MusicRepository: JpaRepository<MusicEntity, Int> {
    fun findByUserId(userId: Int): List<MusicEntity>
    fun findByIdIn(ids: List<Int>): List<MusicEntity>
}