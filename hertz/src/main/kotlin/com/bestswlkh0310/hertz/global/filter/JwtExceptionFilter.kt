package com.bestswlkh0310.hertz.global.filter


import com.bestswlkh0310.hertz.global.response.BaseResponse
import com.bestswlkh0310.hertz.global.exception.ErrorCode
import com.bestswlkh0310.hertz.global.response.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class JwtExceptionFilter(
    private val objectMapper: ObjectMapper
): OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: ExpiredJwtException) {
            setErrorResponse(response, ErrorCode.INVALID_AUTH_TOKEN)
        } catch (e: MalformedJwtException) {
            setErrorResponse(response, ErrorCode.INVALID_AUTH_TOKEN)
        } catch (e: UnsupportedJwtException) {
            setErrorResponse(response, ErrorCode.INVALID_AUTH_TOKEN)
        } catch (e: IllegalArgumentException) {
            setErrorResponse(response, ErrorCode.INVALID_AUTH_TOKEN)
        }
    }

    private fun setErrorResponse(response: HttpServletResponse, error: ErrorCode) {
        try {
            responseToClient(response, ErrorResponse.of(error))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    private fun responseToClient(response: HttpServletResponse, errorResponse: ErrorResponse) {
        response.status = errorResponse.status
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"
        response.writer.write(objectMapper.writeValueAsString(errorResponse))
    }
}