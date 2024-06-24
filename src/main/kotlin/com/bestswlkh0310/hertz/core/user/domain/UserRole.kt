package com.bestswlkh0310.hertz.core.user.domain

enum class UserRole(
    val key: String
) {
    GUEST(key = "ROLE_GUEST"),
    MEMBER(key = "ROLE_MEMBER"),
    ADMIN(key = "ROLE_ADMIN")
}