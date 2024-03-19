package com.bestswlkh0310.hertz.domain.like.core.repository

import com.bestswlkh0310.hertz.domain.like.core.model.LikeEntity
import com.bestswlkh0310.hertz.domain.music.core.model.MusicEntity
import com.bestswlkh0310.hertz.domain.user.core.model.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LikeRepository: CrudRepository<LikeEntity, Int> {
    fun findByMusicAndUser(music: MusicEntity, user: UserEntity): LikeEntity?
}