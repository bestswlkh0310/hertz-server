package com.bestswlkh0310.hertz.infra.jwt

import com.bestswlkh0310.hertz.core.auth.domain.JwtType
import com.bestswlkh0310.hertz.core.auth.domain.Token
import com.bestswlkh0310.hertz.core.auth.port.TokenPort
import com.bestswlkh0310.hertz.infra.exception.CustomException
import com.bestswlkh0310.hertz.infra.exception.ErrorCode
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenUtil(
    @Value("\${jwt.access-token-secret}")
    private val accessTokenSecret: String,

    @Value("\${jwt.refresh-token-secret}")
    private val refreshTokenSecret: String
) : TokenPort {
    override fun createToken(email: String): Token {
        val accessToken = createToken(email, JwtType.ACCESS_TOKEN)
        val refreshToken = createToken(email, JwtType.REFRESH_TOKEN)
        return Token(accessToken, refreshToken)
    }

    private fun createToken(email: String, type: JwtType): String {
        val claims = Jwts.claims()
        claims[JwtConstant.EMAIL] = email

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + getExpiredByType(type)))
            .signWith(SignatureAlgorithm.HS256, getSecretByType(type).toByteArray())
            .compact()
    }

    override fun getEmail(token: String, type: JwtType): String =
        extractClaims(token, type)[JwtConstant.EMAIL].toString()

    override fun isExpired(token: String, type: JwtType): Boolean =
        extractClaims(token, type).expiration.before(Date())

    override fun reissue(refreshToken: String): Token {
        val email = getEmail(refreshToken, JwtType.REFRESH_TOKEN)
        val newAccessToken = createToken(email, JwtType.ACCESS_TOKEN)
        return Token(accessToken = newAccessToken, refreshToken = refreshToken)
    }

    private fun extractClaims(token: String, type: JwtType): Claims =
        try {
            Jwts.parser()
                .setSigningKey(getSecretByType(type).toByteArray())
                .parseClaimsJws(token).body
        } catch (e: Exception) {
            throw CustomException(ErrorCode.INVALID_AUTH_TOKEN)
        }

    private fun getSecretByType(type: JwtType) = when (type) {
        JwtType.ACCESS_TOKEN -> accessTokenSecret
        JwtType.REFRESH_TOKEN -> refreshTokenSecret
    }

    private fun getExpiredByType(type: JwtType) = when (type) {
        JwtType.ACCESS_TOKEN -> JwtConstant.ACCESS_EXPIRED_AT
        JwtType.REFRESH_TOKEN -> JwtConstant.REFRESH_EXPIRED_AT
    }
}