package com.bestswlkh0310.hertz.global.exception

data class CustomException(
    val errorCode: ErrorCode
) : RuntimeException()