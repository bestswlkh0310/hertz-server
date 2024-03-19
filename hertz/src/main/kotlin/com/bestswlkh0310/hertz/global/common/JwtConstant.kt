package com.bestswlkh0310.hertz.global.common

object JwtConstant {
    const val username = "username"

    const val accessExpiredAt = 1000 * 60 * 30L // 30 m
    const val refreshExpiredAt = 1000 * 60 * 60 * 24 * 7L // 1 w

}