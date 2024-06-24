package com.bestswlkh0310.hertz.core.auth.res

import com.bestswlkh0310.hertz.core.auth.domain.Token

data class TokenRes(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun of(token: Token) =
            TokenRes(
                accessToken = token.accessToken,
                refreshToken = token.refreshToken
            )
    }
}
