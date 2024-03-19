package com.bestswlkh0310.hertz.global.filter

import com.bestswlkh0310.hertz.domain.user.core.service.UserService
import com.bestswlkh0310.hertz.global.jwt.JwtTokenUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter


class JwtTokenFilter(
    private val userService: UserService,
    private val accessTokenSecret: String,
    private val jwtTokenUtil: JwtTokenUtil
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION)

        // Header의 Authorization 값이 비어있으면 => Jwt Token을 전송하지 않음 => 로그인 하지 않음
        if (authorizationHeader == null) {
            filterChain.doFilter(request, response)
            return
        }

        // Header의 Authorization 값이 'Bearer '로 시작하지 않으면 => 잘못된 토큰
        if (!authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        // 전송받은 값에서 'Bearer ' 뒷부분(Jwt Token) 추출
        val token = authorizationHeader.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]

        // 전송받은 Jwt Token이 만료되었으면 => 다음 필터 진행(인증 X)
        if (jwtTokenUtil.isExpired(token, accessTokenSecret)) {
            filterChain.doFilter(request, response)
            return
        }

        // Jwt Token에서 loginId 추출
        val username = jwtTokenUtil.getUsername(token, accessTokenSecret)

        // 추출한 loginId로 User 찾아오기
        val user = userService.getUser(username)

        // loginUser 정보로 UsernamePasswordAuthenticationToken 발급
        val authenticationToken = UsernamePasswordAuthenticationToken(
            user.username, null, arrayListOf(SimpleGrantedAuthority(user.role.name))
        )
        authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)

        // 권한 부여
        SecurityContextHolder.getContext().authentication = authenticationToken
        filterChain.doFilter(request, response)
    }
}