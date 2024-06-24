package com.bestswlkh0310.hertz.core.auth.port

import com.bestswlkh0310.hertz.core.auth.domain.JwtType
import com.bestswlkh0310.hertz.core.auth.domain.Token

interface TokenPort {
    fun createToken(email: String): Token
    fun getEmail(token: String, type: JwtType): String
    fun isExpired(token: String, type: JwtType): Boolean
    fun reissue(refreshToken: String): Token
}