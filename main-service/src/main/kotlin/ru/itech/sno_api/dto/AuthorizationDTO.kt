package ru.itech.sno_api.dto
/*
import io.swagger.v3.oas.annotations.media.Schema
import ru.itech.sno_api.entity.AuthorizationEntity

@Schema(description = "Авторизация")
data class AuthorizationDTO(
    @Schema(description = "Идентификатор авторизации")
    val authId: Long,
    @Schema(description = "Пользователь")
    val user: UserDTO,
    @Schema(description = "Токен")
    val token: String,
    @Schema(description = "Двухфакторная аутентификация")
    val twoFactorEnabled: Boolean
){
    fun toEntity(): AuthorizationEntity = AuthorizationEntity(
        authId = this.authId,
        user = this.user.toEntity(),
        token = this.token,
        twoFactorEnabled = this.twoFactorEnabled
    )
}
*/