package com.bestswlkh0310.hertz.domain.music

import com.bestswlkh0310.hertz.domain.music.model.MusicEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MusicRepository: JpaRepository<MusicEntity, Int> {

    fun findByMusic(music: String): MusicEntity

}