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
import com.bestswlkh0310.hertz.global.jwt.JwtType
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import kotlin.reflect.typeOf


@Service
class UserService(
    private val userRepository: UserRepository,
    private val jwtTokenUtil: JwtTokenUtil
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

        val accessToken = jwtTokenUtil.createToken(req.username, JwtType.ACCESS_TOKEN)

        val refreshToken = jwtTokenUtil.createToken(req.username, JwtType.REFRESH_TOKEN)

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

        val accessToken = jwtTokenUtil.createToken(req.username, JwtType.ACCESS_TOKEN)
        val refreshToken = jwtTokenUtil.createToken(req.username, JwtType.REFRESH_TOKEN)

        val tokenResponse = TokenResponse(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
        return tokenResponse
    }

    fun refresh(refreshToken: String): TokenResponse {
        val isExpired = jwtTokenUtil.isExpired(refreshToken, JwtType.REFRESH_TOKEN)
        if (isExpired) {
            throw CustomException(ErrorCode.INVALID_AUTH_TOKEN)
        }

        val username = jwtTokenUtil.getUsername(refreshToken, JwtType.REFRESH_TOKEN)
        val newAccessToken = jwtTokenUtil.createToken(username, JwtType.ACCESS_TOKEN)

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