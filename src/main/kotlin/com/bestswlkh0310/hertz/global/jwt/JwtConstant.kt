package com.bestswlkh0310.hertz.global.jwt

object JwtConstant {
    const val USERNAME = "username"

    const val ACCESS_EXPIRED_AT = 1000 * 60 * 30L // 30 m
    const val REFRESH_EXPIRED_AT = 1000 * 60 * 60 * 24 * 7L // 1 w


}