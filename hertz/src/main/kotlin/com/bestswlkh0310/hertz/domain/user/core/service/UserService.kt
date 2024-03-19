package com.bestswlkh0310.hertz.domain.user.core.service

import com.bestswlkh0310.hertz.domain.user.api.dto.request.SignInRequest
import com.bestswlkh0310.hertz.domain.user.api.dto.request.SignUpRequest
import com.bestswlkh0310.hertz.domain.user.api.dto.response.TokenResponse
import com.bestswlkh0310.hertz.domain.user.core.model.UserEntity
import com.bestswlkh0310.hertz.global.jwt.JwtConstant
import com.bestswlkh0310.hertz.domain.user.core.model.consts.UserRole
import com.bestswlkh0310.hertz.domain.user.core.repository.UserRepository
import com.bestswlkh0310.hertz.global.config.SecurityConfig
import com.bestswlkh0310.hertz.global.exception.CustomException
import com.bestswlkh0310.hertz.global.exception.ErrorCode
import com.bestswlkh0310.hertz.global.jwt.JwtTokenUtil
import org.springframework.stereotype.Service


@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun registerUser(req: SignUpRequest): TokenResponse {
        val isUserExists = userRepository.existsByUsername(req.username)
        if (isUserExists) {
            throw CustomException(ErrorCode.BAD_REQUEST)
        }
        if (req.password != req.passwordCheck) {
            throw CustomException(ErrorCode.BAD_REQUEST)
        }

        val newUser = UserEntity(
            username = req.username,
            password = req.password,
            role = UserRole.ROLE_USER
        )

        userRepository.save(newUser)

        val accessToken = JwtTokenUtil.createToken(
            username = req.username,
            key = SecurityConfig.secretKey,
            expireTimeMs = JwtConstant.ACCESS_EXPIRED_AT
        )

        val refreshToken = JwtTokenUtil.createToken(
            username = req.username,
            key = SecurityConfig.secretKey,
            expireTimeMs = JwtConstant.REFRESH_EXPIRED_AT
        )

        val tokenResponse = TokenResponse(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
        return tokenResponse
    }

    fun signIn(req: SignInRequest): TokenResponse {
        val username = req.username
        val password = req.password

        val user = userRepository.findByUsername(username) ?: throw CustomException(ErrorCode.USER_NOT_FOUND)

        if (user.password != password) {
            throw CustomException(ErrorCode.BAD_REQUEST)
        }

        val accessToken = JwtTokenUtil.createToken(
            username = req.username,
            key = SecurityConfig.secretKey,
            expireTimeMs = JwtConstant.ACCESS_EXPIRED_AT
        )

        val refreshToken = JwtTokenUtil.createToken(
            username = req.username,
            key = SecurityConfig.secretKey,
            expireTimeMs = JwtConstant.REFRESH_EXPIRED_AT
        )

        val tokenResponse = TokenResponse(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
        return tokenResponse
    }

    fun refresh(refreshToken: String): TokenResponse {
        val isExpired = JwtTokenUtil.isExpired(refreshToken, SecurityConfig.secretKey)
        if (isExpired) {
            throw CustomException(ErrorCode.BAD_REQUEST)
        }

        val username = JwtTokenUtil.getUsername(refreshToken, SecurityConfig.secretKey)

        val newAccessToken = JwtTokenUtil.createToken(
            username = username,
            SecurityConfig.secretKey,
            JwtConstant.ACCESS_EXPIRED_AT
        )

        val tokenResponse = TokenResponse(
            accessToken = newAccessToken,
            refreshToken = refreshToken
        )

        return tokenResponse
    }

    fun getUser(username: String): UserEntity {
        val user = userRepository.findByUsername(username)
        user?: throw CustomException(ErrorCode.USER_NOT_FOUND)
        return user
    }

}