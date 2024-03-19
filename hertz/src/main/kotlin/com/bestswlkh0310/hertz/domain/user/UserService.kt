package com.bestswlkh0310.hertz.domain.user

import com.bestswlkh0310.hertz.domain.user.model.SignInRequest
import com.bestswlkh0310.hertz.domain.user.model.SignUpRequest
import com.bestswlkh0310.hertz.domain.user.model.TokenResponse
import com.bestswlkh0310.hertz.domain.user.model.UserEntity
import com.bestswlkh0310.hertz.global.common.JwtConstant
import com.bestswlkh0310.hertz.global.common.UserRole
import com.bestswlkh0310.hertz.global.config.SecurityConfig
import com.bestswlkh0310.hertz.global.exception.CustomException
import com.bestswlkh0310.hertz.global.exception.ErrorCode
import com.bestswlkh0310.hertz.global.util.JwtTokenUtil
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
            expireTimeMs = JwtConstant.accessExpiredAt
        )

        val refreshToken = JwtTokenUtil.createToken(
            username = req.username,
            key = SecurityConfig.secretKey,
            expireTimeMs = JwtConstant.refreshExpiredAt
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
            expireTimeMs = JwtConstant.accessExpiredAt
        )

        val refreshToken = JwtTokenUtil.createToken(
            username = req.username,
            key = SecurityConfig.secretKey,
            expireTimeMs = JwtConstant.refreshExpiredAt
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
            JwtConstant.accessExpiredAt
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