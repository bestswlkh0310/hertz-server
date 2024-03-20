package com.bestswlkh0310.hertz.global.jwt

import com.bestswlkh0310.hertz.global.exception.CustomException
import com.bestswlkh0310.hertz.global.exception.ErrorCode
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenUtil {

    @Value("\${jwt.access-token-secret}")
    lateinit var accessTokenSecret: String
    @Value("\${jwt.refresh-token-secret}")
    lateinit var refreshTokenSecret: String

    fun createToken(username: String, type: JwtType): String {
        val claims = Jwts.claims()
        claims[JwtConstant.USERNAME] = username

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + getExpiredByType(type)))
            .signWith(SignatureAlgorithm.HS256, getSecretByType(type).toByteArray())
            .compact()
    }

    fun getUsername(token: String, type: JwtType): String {
        return extractClaims(token, type)[JwtConstant.USERNAME].toString()
    }

    fun isExpired(token: String, type: JwtType): Boolean {
        val expiredDate: Date = extractClaims(token, type).expiration
        return expiredDate.before(Date())
    }

    private fun extractClaims(token: String, type: JwtType): Claims {
        return Jwts.parser()
            .setSigningKey(getSecretByType(type).toByteArray())
            .parseClaimsJws(token).body
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