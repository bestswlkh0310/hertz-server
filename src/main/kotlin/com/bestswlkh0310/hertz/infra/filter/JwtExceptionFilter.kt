package com.bestswlkh0310.hertz.infra.filter

import com.bestswlkh0310.hertz.infra.exception.ErrorCode
import com.bestswlkh0310.hertz.infra.response.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtExceptionFilter(
    private val objectMapper: ObjectMapper
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            e.printStackTrace()
            setErrorResponse(response)
        }
    }

    private fun setErrorResponse(response: HttpServletResponse) {
        try {
            val errorResponse = ErrorResponse.of(ErrorCode.INVALID_AUTH_TOKEN)
            response.status = errorResponse.status
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.characterEncoding = "UTF-8"
            response.writer.write(objectMapper.writeValueAsString(errorResponse))
            response.writer.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}