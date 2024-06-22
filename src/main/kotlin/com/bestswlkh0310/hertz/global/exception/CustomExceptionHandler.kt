package com.bestswlkh0310.hertz.global.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpClientErrorException.Forbidden


@ControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(CustomException::class)
    protected fun handleCustomException(e: CustomException): ResponseEntity<ErrorResponse> {
        return ErrorResponse.makeResponseEntity(e.errorCode)
    }

    @ExceptionHandler(Forbidden::class)
    fun handleForbiddenException() = ErrorResponse.makeResponseEntity(ErrorCode.FORBIDDEN)

    @ExceptionHandler(Exception::class)
    fun handleUnexpectedException() = ErrorResponse.makeResponseEntity(ErrorCode.INTERNAL_SERVER_ERROR)
}