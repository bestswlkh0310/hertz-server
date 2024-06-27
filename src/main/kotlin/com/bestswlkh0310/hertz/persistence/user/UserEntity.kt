package com.bestswlkh0310.hertz.persistence.user

import com.bestswlkh0310.hertz.core.user.domain.User
import com.bestswlkh0310.hertz.core.user.domain.UserRole
import com.bestswlkh0310.hertz.persistence.common.BaseIdAndTimeEntity
import com.bestswlkh0310.hertz.persistence.common.TableName
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.time.LocalDateTime

@Entity(name = TableName.USER)
class UserEntity(
    override val id: Int,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val role: UserRole,

    override val createdAt: LocalDateTime
) : BaseIdAndTimeEntity(id) {
    fun toDomain() = User(
        id = id,
        email = email,
        password = password,
        role = role,
        createdAt = createdAt
    )
    companion object {
        fun of(user: User) = UserEntity(
            id = user.id,
            email = user.email,
            password = user.password,
            role = user.role,
            createdAt = user.createdAt
        )
    }
}