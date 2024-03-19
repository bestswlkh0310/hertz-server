package com.bestswlkh0310.hertz.domain.user.model

import com.bestswlkh0310.hertz.global.common.UserRole
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val username: String,
    val password: String,
    val role: UserRole
)
