package com.bestswlkh0310.hertz.core.user.port

import com.bestswlkh0310.hertz.core.user.domain.User

interface UserPort {
    fun get(id: Int): User?
    fun getByEmail(email: String): User?
    fun insert(user: User): User
    fun exists(email: String, password: String): Boolean
}