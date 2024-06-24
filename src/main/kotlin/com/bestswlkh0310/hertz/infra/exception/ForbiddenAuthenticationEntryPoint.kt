package com.bestswlkh0310.hertz.infra.exception

import com.bestswlkh0310.hertz.infra.response.ErrorResponse
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

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        authException: AuthenticationException?
    ) {
        println("ForbiddenAuthenticationEntryPoint.commence")
        setResponse(response)
    }

    @Throws(IOException::class)
    private fun setResponse(response: HttpServletResponse) {
        val errorResponse = ErrorResponse.of(ErrorCode.FORBIDDEN)
        response.status = errorResponse.status
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"
        response.writer.write(objectMapper.writeValueAsString(errorResponse))
        response.writer.flush()
    }
}