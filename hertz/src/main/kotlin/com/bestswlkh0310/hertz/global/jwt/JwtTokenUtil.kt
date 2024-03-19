package com.bestswlkh0310.hertz.global.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.*

object JwtTokenUtil {

    fun createToken(username: String, key: String, expireTimeMs: Long): String {
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