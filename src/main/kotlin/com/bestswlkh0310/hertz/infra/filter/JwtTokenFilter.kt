package com.bestswlkh0310.hertz.infra.filter

import com.bestswlkh0310.hertz.infra.jwt.JwtTokenUtil
import com.bestswlkh0310.hertz.core.auth.domain.JwtType
import com.bestswlkh0310.hertz.core.user.port.UserPort
import com.bestswlkh0310.hertz.infra.exception.CustomException
import com.bestswlkh0310.hertz.infra.exception.ErrorCode
import com.bestswlkh0310.hertz.infra.security.CustomUserDetails
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


@Component
class JwtTokenFilter(
    private val jwtTokenUtil: JwtTokenUtil,
    private val userPort: UserPort
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION)

        // validation
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val token = authorizationHeader.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]

        if (jwtTokenUtil.isExpired(token, JwtType.ACCESS_TOKEN)) {
            filterChain.doFilter(request, response)
            return
        }

        // find user
        val email = jwtTokenUtil.getEmail(token, JwtType.ACCESS_TOKEN)
        val user = userPort.getByEmail(email) ?: throw CustomException(ErrorCode.NOT_FOUND)

        val authenticationToken = UsernamePasswordAuthenticationToken(
            CustomUserDetails(user), null, listOf(SimpleGrantedAuthority(user.role.name))
        )
        authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)

        // give permission
        SecurityContextHolder.getContext().authentication = authenticationToken
        filterChain.doFilter(request, response)
    }
}