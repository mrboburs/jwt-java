package com.example.todoapp.controller.auth

import com.example.todoapp.service.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationService: AuthenticationService

) {


    @PostMapping()
    fun authenticate(@RequestBody authRequest: AuthenticationRequest): AuthenticationResponse {
        return authenticationService.authenticate(authRequest)
    }

    @PostMapping("refresh")
    fun refreshAccessToken(
        @RequestBody request: RefreshTokenRequest
    ): TokenResponse =
        authenticationService.refreshAccessToken(request.token)
            ?.mapToTokenResponse()
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid refresh token")


    private fun String.mapToTokenResponse(): TokenResponse =
        TokenResponse(
            token = this,
        )
}
