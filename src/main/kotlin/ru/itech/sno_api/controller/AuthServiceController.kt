package ru.itech.sno_api.controller

import org.springframework.web.bind.annotation.*
import ru.itech.sno_api.core.domain.request.user.SignInRequest
import ru.itech.sno_api.core.domain.request.user.SignUpRequest
import ru.itech.sno_api.core.util.AuthTokenResponse
import ru.itech.sno_api.service.AuthService

@RestController
@RequestMapping("/api/auth")
class AuthServiceController(
    private val authService: AuthService
) {

    @PostMapping("/login")
    fun authenticate(@RequestBody signInRequest: SignInRequest): AuthTokenResponse {
        return authService.authenticate(signInRequest)
    }

    @PostMapping("/refresh")
    fun refreshToken(@RequestParam("refresh_token") refreshToken: String): AuthTokenResponse {
        return authService.refreshToken(refreshToken)
    }

    @PostMapping("/register")
    fun registerUser(@RequestBody signUpRequest: SignUpRequest): AuthTokenResponse {
        return authService.registerUser(signUpRequest)
    }
}
