package com.bestswlkh0310.hertz.global.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(CustomException::class)
    protected fun handleCustomException(e: CustomException): ResponseEntity<ErrorResponseEntity> {
        return ErrorResponseEntity.toResponseEntity(e.errorCode)
    }
}