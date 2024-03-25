package com.bestswlkh0310.hertz.domain.playlist.core.repository

import com.bestswlkh0310.hertz.domain.playlist.core.model.PlaylistEntity
import com.bestswlkh0310.hertz.domain.user.core.model.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PlaylistRepository: CrudRepository<PlaylistEntity, Int> {

    fun findByUser(userEntity: UserEntity): List<PlaylistEntity>

}