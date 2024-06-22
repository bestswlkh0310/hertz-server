package com.bestswlkh0310.hertz.domain.user.core.repository

import com.bestswlkh0310.hertz.domain.user.core.model.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<UserEntity, Int> {

    fun findByUsername(username: String): UserEntity?

    fun existsByUsername(username: String): Boolean
}