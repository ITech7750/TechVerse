package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.itech.sno_api.entity.OrganizationEntity
import ru.itech.sno_api.entity.UserEntity


@Schema(description = "Полная информация о пользователе")
data class UserDTO(
    @Schema(description = "Логин пользователя", example = "john_doe")
    val login: String = "",

    @Schema(description = "Пароль для аутентификации пользователя", example = "secure_password")
    var password: String = "",

    @Schema(description = "Электронная почта пользователя", example = "john.doe@example.com")
    val email: String = "",

    @Schema(description = "Уникальный идентификатор пользователя", example = "1001")
    val userId: Long = 0,

    @Schema(description = "Имя пользователя", example = "John", required = false)
    val firstName: String? = null,

    @Schema(description = "Фамилия пользователя", example = "Doe", required = false)
    val lastName: String? = null,

    @Schema(description = "Отчество пользователя", example = "Michael", required = false)
    val middleName: String? = null,

    @Schema(description = "Роль пользователя в системе", example = "Администратор", required = false)
    val role: String? = null,

    @Schema(description = "Показывает, является ли пользователь студентом МИФИ", example = "true", required = false)
    val isStudentMifi: Boolean? = null,

    @Schema(description = "Идентификатор организации, к которой принадлежит пользователь", example = "5", required = false)
    val organizationId: Long? = null,

    @Schema(description = "Указывает, включена ли двухфакторная аутентификация", example = "true", required = false)
    val twoFactorAuthEnabled: Boolean? = null,

    @Schema(description = "Набор идентификаторов курсов, ассоциированных с пользователем", example = "[101, 102, 103]", required = false)
    val courses: Set<Long> = emptySet()
)
fun UserDTO.toEntity(): UserEntity {
    return UserEntity(
        userId = this.userId,
        login = this.login,
        email = this.email,
        password = this.password,
        firstName = this.firstName,
        lastName = this.lastName,
        middleName = this.middleName,
        role = this.role,
        isStudentMifi = this.isStudentMifi,
        twoFactorAuthEnabled = this.twoFactorAuthEnabled,
        organization = if (this.organizationId != null) OrganizationEntity().apply { organizationId = this.organizationId } else null
    )
}

