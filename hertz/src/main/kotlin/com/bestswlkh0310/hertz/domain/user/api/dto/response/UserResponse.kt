package com.bestswlkh0310.hertz.domain.user.api.dto.response

import com.bestswlkh0310.hertz.domain.user.core.model.UserEntity

data class UserResponse(
    val id: Int,
    val username: String,
    val password: String
) {
    companion object {
        fun fromEntity(u: UserEntity): UserResponse
        = UserResponse(
            id = u.id?: -1,
            username = u.username,
            password = u.password
        )
    }
}
