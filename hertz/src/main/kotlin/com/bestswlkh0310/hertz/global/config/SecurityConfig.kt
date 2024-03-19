package com.bestswlkh0310.hertz.global.config

import com.bestswlkh0310.hertz.domain.user.UserService
import com.bestswlkh0310.hertz.global.JwtTokenFilter
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
    private val userService: UserService
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
            .addFilterBefore(
                JwtTokenFilter(userService, secretKey),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .authorizeHttpRequests {
                it.requestMatchers("/api/v1/user/sign-up", "/api/v1/user/sign-in", "/api/v1/user/refresh").permitAll()
                    .anyRequest()
                    .authenticated()
            }
            .build()
    }

    companion object {
        const val secretKey = "my-secret-key-123123asoidasdiojasdioj"
    }
}