package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.itech.sno_api.entity.OrganizationEntity
import ru.itech.sno_api.entity.UserInfoEntity


@Schema(description = "User Information Data Transfer Object")
data class UserInfoDTO(
    @Schema(description = "User first name", example = "John")
    val firstName: String,

    @Schema(description = "User last name", example = "Doe")
    val lastName: String,

    @Schema(description = "User middle name", example = "Michael")
    val middleName: String?,

    @Schema(description = "User role", example = "admin")
    val role: String,

    @Schema(description = "Whether the user is a student at MIFI", example = "true")
    val isStudentMifi: Boolean,

    @Schema(description = "ID of the organization the user belongs to", example = "1")
    val organizationId: OrganizationEntity?,

    @Schema(description = "Whether two-factor authentication is enabled for the user", example = "true")
    val twoFactorAuthEnabled: Boolean
)





