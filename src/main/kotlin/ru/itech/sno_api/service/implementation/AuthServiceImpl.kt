package ru.itech.sno_api.service.implementation

import jakarta.persistence.EntityNotFoundException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.itech.sno_api.core.JwtHelper
import ru.itech.sno_api.core.domain.User
import ru.itech.sno_api.core.domain.request.user.SignInRequest
import ru.itech.sno_api.core.domain.request.user.SignUpRequest
import ru.itech.sno_api.core.util.AuthTokenResponse
import ru.itech.sno_api.dto.UserDTO
import ru.itech.sno_api.repository.UserInfoRepository
import ru.itech.sno_api.service.AuthService
import ru.itech.sno_api.entity.UserEntity
import ru.itech.sno_api.repository.UserRepository


@Service
class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val jwtHelper: JwtHelper,
    private val passwordEncoder: PasswordEncoder
) : AuthService {

    override fun authenticate(signInRequest: SignInRequest): AuthTokenResponse {
        val userEntity = userRepository.findByLogin(signInRequest.login)
            .orElseThrow { throw EntityNotFoundException("User with login ${signInRequest.login} not found") }

        if (userEntity.password != signInRequest.password) {
            throw BadCredentialsException("Invalid username or password")
        }

        val user = User(
            id = userEntity.userId ?: throw EntityNotFoundException("User ID not found"),
            login = userEntity.login,
            email = userEntity.email
        )

        val accessToken = jwtHelper.createToken(user, HashMap(), isAccessToken = true)
        val refreshToken = jwtHelper.createToken(user, HashMap(), isAccessToken = false)

        return AuthTokenResponse(accessToken, refreshToken)
    }

    override fun refreshToken(refreshToken: String):AuthTokenResponse {
        if (!jwtHelper.isRefreshToken(refreshToken)) {
            throw IllegalArgumentException("Invalid refresh token")
        }

        val claims = jwtHelper.getClaims(refreshToken)
            ?: throw IllegalArgumentException("Invalid refresh token")

        val userEntity = userRepository.findById(claims["id"] as Long)
            .orElseThrow { throw EntityNotFoundException("User with ID ${claims["id"]} not found") }

        val user = User(
            id = userEntity.userId ?: throw EntityNotFoundException("User ID not found"),
            email = userEntity.email
        )

        val newAccessToken = jwtHelper.createToken(user, HashMap(), isAccessToken = true)

        return AuthTokenResponse(newAccessToken, refreshToken)
    }

    override fun registerUser(signUpRequest: SignUpRequest): AuthTokenResponse {

        if (userRepository.findByEmail(signUpRequest.email).isPresent) {
            throw IllegalArgumentException("User with email ${signUpRequest.email} already exists")
        }


        val hashedPassword = passwordEncoder.encode(signUpRequest.password)


        val userEntity = UserDTO(
            login = signUpRequest.login,
            email = signUpRequest.email,
            password = hashedPassword
        ).toEntity(passwordEncoder)


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
