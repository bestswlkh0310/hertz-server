package com.bestswlkh0310.hertz.domain.user.model

data class UserResponse(
    val id: Int?,
    val username: String,
    val password: String
) {
    companion object {
        fun fromEntity(u: UserEntity): UserResponse
        = UserResponse(
            id = u.id,
            username = u.username,
            password = u.password
        )
    }
}
