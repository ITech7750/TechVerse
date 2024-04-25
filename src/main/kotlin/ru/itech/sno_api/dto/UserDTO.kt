package ru.itech.sno_api.dto

import org.springframework.security.crypto.password.PasswordEncoder
import ru.itech.sno_api.entity.UserEntity

data class UserDTO(
    val login: String,
    val password: String,
    val email: String,
)
fun UserDTO.toEntity(): UserEntity {
    return UserEntity(
        login = this.login,
        email = this.email,
        password = this.password
    )
}
