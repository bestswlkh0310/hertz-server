package com.bestswlkh0310.hertz.persistence.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<UserEntity, Int> {
    fun existsByEmailAndPassword(email: String, password: String): Boolean
    fun findByEmail(email: String): UserEntity?
}