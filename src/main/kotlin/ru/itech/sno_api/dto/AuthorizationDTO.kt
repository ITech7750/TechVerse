package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Авторизация")
data class AuthorizationDTO(
    @Schema(description = "Идентификатор авторизации")
    val authId: Long,
    @Schema(description = "Пользователь")
    val user: UserInfoDTO,
    @Schema(description = "Токен")
    val token: String,
    @Schema(description = "Двухфакторная аутентификация")
    val twoFactorEnabled: Boolean
)
