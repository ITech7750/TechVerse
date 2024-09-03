package ru.itech.sno_api.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import ru.itech.sno_api.core.domain.request.PasswordResetRequest
import ru.itech.sno_api.core.domain.request.user.SignInRequest
import ru.itech.sno_api.core.domain.request.user.SignUpRequest
import ru.itech.sno_api.core.util.AuthTokenResponse
import ru.itech.sno_api.service.AuthService

@RestController
@RequestMapping("/api/auth")
@Tag(
    name = "Auth Service API",
    description = "Регистрация, авторизация и аутентификация",
)
class AuthServiceController(
    private val authService: AuthService,
) {
    @PostMapping("/login")
    fun authenticate(
        @RequestBody signInRequest: SignInRequest,
    ): Mono<AuthTokenResponse> = authService.authenticate(signInRequest)

    @PostMapping("/refresh")
    fun refreshToken(
        @RequestParam("refresh_token") refreshToken: String,
    ): Mono<AuthTokenResponse> = authService.refreshToken(refreshToken)

    @PostMapping("/register")
    fun registerUser(
        @RequestBody signUpRequest: SignUpRequest,
    ): Mono<AuthTokenResponse> = authService.registerUser(signUpRequest)

    @PostMapping("/reset-password")
    fun requestPasswordReset(
        @RequestBody email: String,
    ): Mono<Void> = authService.requestPasswordReset(email)

    @PostMapping("/reset-password/confirm")
    fun confirmPasswordReset(
        @RequestBody request: PasswordResetRequest,
    ): Mono<Void> = authService.confirmPasswordReset(request)
}
