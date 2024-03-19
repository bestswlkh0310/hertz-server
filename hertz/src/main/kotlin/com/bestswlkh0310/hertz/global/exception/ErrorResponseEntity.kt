package com.bestswlkh0310.hertz.global.exception

import org.springframework.http.ResponseEntity


data class ErrorResponseEntity(
    val status: Int,
    val code: String,
    val message: String,
) {
    companion object {
        fun toResponseEntity(e: ErrorCode): ResponseEntity<ErrorResponseEntity> {
            val body = ErrorResponseEntity(
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