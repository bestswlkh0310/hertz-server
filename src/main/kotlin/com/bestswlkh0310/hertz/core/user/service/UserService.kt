package com.bestswlkh0310.hertz.core.user.service

import com.bestswlkh0310.hertz.core.user.res.UserRes

interface UserService {
    fun get(id: Int): UserRes
    fun leave(): String
}