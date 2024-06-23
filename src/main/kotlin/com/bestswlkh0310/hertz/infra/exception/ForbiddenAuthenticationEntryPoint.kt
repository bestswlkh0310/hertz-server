package com.bestswlkh0310.hertz.infra.exception

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException


@Component
class ForbiddenAuthenticationEntryPoint(
    private val objectMapper: ObjectMapper
) : AuthenticationEntryPoint {

    @Throws(IOException::class, ServletException::class)
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        authException: AuthenticationException?
    ) {
        setResponse(response)
    }

    @Throws(IOException::class)
    private fun setResponse(response: HttpServletResponse) {
        val errorCode = ErrorCode.FORBIDDEN

        response.status = errorCode.httpStatus.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"

        val errorResponse = ErrorResponse(
            status = errorCode.httpStatus.value(),
            code = errorCode.httpStatus.reasonPhrase,
            message = errorCode.message
        )
        val responseBody = objectMapper.writeValueAsString(errorResponse)

        response.writer.write(responseBody)
        response.writer.flush()
    }
}