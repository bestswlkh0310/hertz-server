package com.bestswlkh0310.hertz.domain.user

import com.bestswlkh0310.hertz.domain.user.model.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<UserEntity, Int> {

    fun findByUsername(username: String): UserEntity?

    fun existsByUsername(username: String): Boolean
}