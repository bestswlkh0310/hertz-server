package com.bestswlkh0310.hertz.infra.exception

import com.bestswlkh0310.hertz.infra.response.ErrorResponse
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpClientErrorException.Forbidden
import org.springframework.web.client.HttpClientErrorException.NotFound
import org.springframework.web.client.HttpClientErrorException.Unauthorized
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.resource.NoResourceFoundException


@RestControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException): ResponseEntity<ErrorResponse> {
        println("CustomException")
        println(e.printStackTrace())
        return ErrorResponse.of(e.errorCode)
            .let { ResponseEntity.status(e.errorCode.httpStatus).body(it) }
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNoHandlerFoundException(e: NoHandlerFoundException): ResponseEntity<ErrorResponse> {
        println("NoHandlerFoundException")
        println(e.printStackTrace())
        return ErrorResponse.of(ErrorCode.NOT_FOUND)
            .let { ResponseEntity.status(HttpStatus.NOT_FOUND).body(it) }
    }

    @ExceptionHandler(Exception::class)
    fun handleUnexpectedException(e: Exception): ResponseEntity<ErrorResponse> {
        println("Exception")
        e.printStackTrace()
        return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR)
            .let { ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(it) }
    }
}