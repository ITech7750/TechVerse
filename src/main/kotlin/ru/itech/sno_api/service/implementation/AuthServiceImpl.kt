package ru.itech.sno_api.service.implementation

import jakarta.persistence.EntityNotFoundException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.itech.sno_api.core.JwtHelper
import ru.itech.sno_api.core.domain.User
import ru.itech.sno_api.core.domain.request.user.SignInRequest
import ru.itech.sno_api.core.domain.request.user.SignUpRequest
import ru.itech.sno_api.core.util.AuthTokenResponse
import ru.itech.sno_api.dto.UserDTO
import ru.itech.sno_api.dto.toEntity
import ru.itech.sno_api.repository.CourseRepository
import ru.itech.sno_api.repository.UserRepository
import ru.itech.sno_api.service.AuthService


@Service
@Transactional
open class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val courseRepository: CourseRepository,
    private val jwtHelper: JwtHelper,
    private val passwordEncoder: PasswordEncoder  // Исправлено: Теперь тип - PasswordEncoder
) : AuthService {

    override fun authenticate(signInRequest: SignInRequest): AuthTokenResponse {
        val userEntity = userRepository.findByLogin(signInRequest.login)
            .orElseThrow { EntityNotFoundException("User with login ${signInRequest.login} not found") }
        println(passwordEncoder.encode(signInRequest.password))
        println(signInRequest.password)
        println(userEntity.password)
        if (!passwordEncoder.matches(signInRequest.password, userEntity.password)) {
            throw BadCredentialsException("Invalid username or password")
        }

        val user = User(
            id = userEntity.userId,
            login = userEntity.login,
            email = userEntity.email
        )

        val accessToken = jwtHelper.createToken(user, HashMap(), isAccessToken = true)
        val refreshToken = jwtHelper.createToken(user, HashMap(), isAccessToken = false)

        return AuthTokenResponse(accessToken, refreshToken)
    }

    override fun refreshToken(refreshToken: String): AuthTokenResponse {
        if (!jwtHelper.isRefreshToken(refreshToken)) {
            throw IllegalArgumentException("Invalid token type. Expected a refresh token.")
        }

        if (jwtHelper.isTokenValid(refreshToken)) {
            throw IllegalArgumentException("Refresh token has expired. Please login again.")
        }

        val claims = jwtHelper.getClaims(refreshToken)
            ?: throw IllegalArgumentException("Failed to parse claims from the refresh token.")

        val userId = claims["id"] as Long? ?: throw IllegalArgumentException("User ID is missing in the refresh token.")

        val userEntity = userRepository.findById(userId)
            .orElseThrow { EntityNotFoundException("User with ID $userId not found.") }

        val user = User(
            id = userEntity.userId,
            login = userEntity.login,
            email = userEntity.email
        )

        // Генерация нового токена доступа и обновления refresh token
        val newAccessToken = jwtHelper.createToken(user, HashMap(), isAccessToken = true)
        val newRefreshToken = jwtHelper.createToken(user, HashMap(), isAccessToken = false)

        return AuthTokenResponse(newAccessToken, newRefreshToken)
    }


    override fun registerUser(signUpRequest: SignUpRequest): AuthTokenResponse {
        if (userRepository.findByEmail(signUpRequest.email).isPresent) {
            throw IllegalArgumentException("User with email ${signUpRequest.email} already exists")
        }

        // Хешируем пароль один раз перед сохранением
        val hashedPassword = passwordEncoder.encode(signUpRequest.password)

        val userEntity = UserDTO(
            login = signUpRequest.login,
            email = signUpRequest.email,
            password = hashedPassword
        ).toEntity(courseRepository)

        val savedUserEntity = userRepository.save(userEntity)

        val user = User(
            id = savedUserEntity.userId,
            login = savedUserEntity.login,
            email = savedUserEntity.email
        )

        val accessToken = jwtHelper.createToken(user, HashMap(), isAccessToken = true)
        val refreshToken = jwtHelper.createToken(user, HashMap(), isAccessToken = false)

        return AuthTokenResponse(accessToken, refreshToken)
    }


}
