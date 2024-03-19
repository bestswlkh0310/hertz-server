package com.bestswlkh0310.hertz.global.jwt

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
        return when (type) {
            JwtType.ACCESS_TOKEN -> createToken(username, accessTokenSecret, JwtConstant.ACCESS_EXPIRED_AT)
            JwtType.REFRESH_TOKEN -> createToken(username, refreshTokenSecret, JwtConstant.REFRESH_EXPIRED_AT)
        }
    }

    private fun createToken(username: String, key: String, expireTimeMs: Long): String {
        val claims = Jwts.claims()
        claims[JwtConstant.USERNAME] = username

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expireTimeMs))
            .signWith(SignatureAlgorithm.HS256, key)
            .compact()
    }

    fun getUsername(token: String, secretKey: String): String {
        return extractClaims(token, secretKey)[JwtConstant.USERNAME].toString()
    }

    fun isExpired(token: String, secretKey: String): Boolean {
        val expiredDate: Date = extractClaims(token, secretKey).expiration
        return expiredDate.before(Date())
    }

    private fun extractClaims(token: String, secretKey: String): Claims {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body
    }
}