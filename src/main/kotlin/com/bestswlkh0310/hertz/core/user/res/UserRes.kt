package com.bestswlkh0310.hertz.core.user.res

import com.bestswlkh0310.hertz.core.user.domain.User
import com.bestswlkh0310.hertz.core.user.domain.UserRole

data class UserRes(
    val id: Int,
    val email: String,
    val password: String,
    val role: UserRole
) {
    companion object {
        fun of(user: User) = UserRes(
            id = user.id,
            email = user.email,
            password = user.password,
            role = user.role
        )
    }
}
