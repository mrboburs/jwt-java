package com.example.todoapp.service

import com.example.todoapp.config.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*


@Service
class TokenService(
    jwtProperties: JwtProperties,

    ) {
    private val secretKey = Keys.hmacShaKeyFor(
        jwtProperties.key.toByteArray(),
    )

    fun generate(
        userDetails: UserDetails,
        expirationDate: Date,
        additionalClaims: Map<String, Any> = emptyMap()
    ): String =
        Jwts.builder()
            .setClaims(additionalClaims)  // Set additional claims (if any)
            .setSubject(userDetails.username)  // Set the subject (username or email)
            .setIssuedAt(Date(System.currentTimeMillis()))  // Set the issue date
            .setExpiration(expirationDate)  // Set the expiration date
            .signWith(secretKey)  // Sign the token with the secret key
            .compact()  // Compact the token into a string

    fun extractEmail(token: String): String? =
        getAllClaims(token)
            .subject

    fun isExpired(token: String): Boolean =
        getAllClaims(token)
            .expiration
            .before(Date(System.currentTimeMillis()))

    fun isValid(token: String, userDetails: UserDetails): Boolean {
        val email = extractEmail(token)
        return userDetails.username == email && !isExpired(token)
    }


    private fun getAllClaims(token: String): Claims {
        return Jwts.parserBuilder()  // Correctly use parserBuilder for modern versions of jjwt
            .setSigningKey(secretKey)  // Set the signing key to verify the token
            .build()  // Build the parser
            .parseClaimsJws(token)  // Parse the JWT and extract claims
            .body  // Return the claims
    }




}