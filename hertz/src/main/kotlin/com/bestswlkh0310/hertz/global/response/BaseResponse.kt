package com.bestswlkh0310.hertz.global.response

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

data class BaseResponse<T>(
    val status: Int,
    val message: String,
    val data: T
) {
    companion object {
        fun <T> ok(data: T = Unit as @ParameterName(name = "data") T, headers: HttpHeaders? = null, httpStatus: HttpStatus = HttpStatus.OK): ResponseEntity<Any> {
            val body = BaseResponse(
                status = httpStatus.value(),
                message = httpStatus.reasonPhrase,
                data = data
            )
            return ResponseEntity(body, headers, httpStatus)
        }
    }
}