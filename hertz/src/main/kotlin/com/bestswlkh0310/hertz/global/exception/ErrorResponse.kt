package com.bestswlkh0310.hertz.global.exception

import org.springframework.http.ResponseEntity


data class ErrorResponse(
    val status: Int,
    val code: String,
    val message: String,
) {
    companion object {
        fun fromErrorCode(e: ErrorCode): ResponseEntity<ErrorResponse> {
            val body = ErrorResponse(
                status = e.httpStatus.value(),
                code = e.name,
                message = e.message
            )
            return ResponseEntity
                .status(e.httpStatus)
                .body(body)
        }
    }
}