package com.bestswlkh0310.hertz.core.user.port

import com.bestswlkh0310.hertz.core.user.domain.User

interface GetCurrentUserPort {
    fun get(): User?
    fun getId(): Int?
}