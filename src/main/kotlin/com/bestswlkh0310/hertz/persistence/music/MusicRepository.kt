package com.bestswlkh0310.hertz.persistence.music

import org.springframework.data.jpa.repository.JpaRepository

interface MusicRepository: JpaRepository<MusicEntity, Int> {
}