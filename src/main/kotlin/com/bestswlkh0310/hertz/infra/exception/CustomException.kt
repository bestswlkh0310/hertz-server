package com.bestswlkh0310.hertz.infra.exception

data class CustomException(
    val errorCode: ErrorCode
) : RuntimeException()