package com.bestswlkh0310.hertz.global.base

data class BaseResponse<T>(
    val status: Int,
    val message: String,
    val data: T = Unit as @ParameterName(name = "data") T
)