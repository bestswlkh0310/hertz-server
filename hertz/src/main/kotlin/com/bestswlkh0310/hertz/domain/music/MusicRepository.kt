package com.bestswlkh0310.hertz.domain.music

import com.bestswlkh0310.hertz.domain.music.model.MusicEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MusicRepository: CrudRepository<MusicEntity, Int> {

}