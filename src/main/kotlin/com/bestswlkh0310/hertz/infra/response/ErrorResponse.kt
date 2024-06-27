package com.bestswlkh0310.hertz.infra.response

import com.bestswlkh0310.hertz.infra.exception.ErrorCode

data class ErrorResponse(
    val status: Int,
    val message: String
) {
    companion object {
        fun of(errorCode: ErrorCode, message: String = errorCode.message) = ErrorResponse(
            status = errorCode.httpStatus.value(),
            message = message
        )
    }
}