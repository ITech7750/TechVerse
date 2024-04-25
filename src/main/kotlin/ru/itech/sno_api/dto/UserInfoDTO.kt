package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.itech.sno_api.entity.OrganizationEntity
import ru.itech.sno_api.entity.UserInfoEntity

@Schema(description = "Информация о пользователе")
data class UserInfoDTO(
    @Schema(description = "Идентификатор пользователя", example = "1")
    val userId: Long?,

    @Schema(description = "Имя пользователя", example = "Иван")
    val firstName: String,

    @Schema(description = "Фамилия пользователя", example = "Иванов")
    val lastName: String,

    @Schema(description = "Отчество пользователя", example = "Иванович")
    val middleName: String?,

    @Schema(description = "Роль пользователя", example = "администратор")
    val role: String,

    @Schema(description = "Является ли пользователь студентом МИФИ", example = "true")
    val isStudentMifi: Boolean,

    @Schema(description = "Идентификатор организации, к которой принадлежит пользователь", example = "1")
    val organizationId: Long?,

    @Schema(description = "Включена ли двухфакторная аутентификация для пользователя", example = "true")
    val twoFactorAuthEnabled: Boolean
)

fun UserInfoDTO.toEntity(existingUser: UserInfoEntity? = null): UserInfoEntity {
    val user = existingUser ?: UserInfoEntity()
    user.firstName = this.firstName
    user.lastName = this.lastName
    user.middleName = this.middleName
    user.role = this.role
    user.isStudentMifi = this.isStudentMifi
    user.twoFactorAuthEnabled = this.twoFactorAuthEnabled
    user.organization = if (this.organizationId != null) OrganizationEntity().apply { organizationId = this@toEntity.organizationId } else null
    return user
}
