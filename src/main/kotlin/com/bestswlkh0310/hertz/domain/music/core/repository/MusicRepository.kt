package com.bestswlkh0310.hertz.domain.music.core.repository

import com.bestswlkh0310.hertz.domain.music.core.model.MusicEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MusicRepository: CrudRepository<MusicEntity, Int> {

}