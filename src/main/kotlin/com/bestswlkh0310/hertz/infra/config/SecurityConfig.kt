package com.bestswlkh0310.hertz.infra.config

import com.bestswlkh0310.hertz.infra.common.Api
import com.bestswlkh0310.hertz.infra.exception.ForbiddenAuthenticationEntryPoint
import com.bestswlkh0310.hertz.infra.filter.JwtExceptionFilter
import com.bestswlkh0310.hertz.infra.filter.JwtTokenFilter
import com.bestswlkh0310.hertz.infra.jwt.JwtTokenUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenUtil: JwtTokenUtil,
    private val jwtTokenFilter: JwtTokenFilter,
    private val jwtExceptionFilter: JwtExceptionFilter,
    private val forbiddenAuthenticationEntryPoint: ForbiddenAuthenticationEntryPoint
) {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .httpBasic {
                it.disable()
            }
            .csrf {
                it.disable()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .addFilterAfter(
                jwtTokenFilter,
                UsernamePasswordAuthenticationFilter::class.java
            )
            .addFilterBefore(
                jwtExceptionFilter,
                JwtTokenFilter::class.java
            )
            .authorizeHttpRequests {
                it.requestMatchers(
                    "${Api.User.PATH}${Api.User.SIGN_UP}",
                    "${Api.User.PATH}${Api.User.SIGN_IN}",
                    "${Api.User.PATH}${Api.User.REFRESH}",
                    "${Api.User.PATH}${Api.User.SEND_EMAIL_CODE}",
                    "/error"
                ).permitAll()
                    .anyRequest()
                    .authenticated()
            }
            .exceptionHandling {
                it.authenticationEntryPoint(forbiddenAuthenticationEntryPoint)
            }
            .build()
    }
}