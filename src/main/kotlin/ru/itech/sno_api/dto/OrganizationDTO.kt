package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Организация")
data class OrganizationDTO(
    @Schema(description = "Идентификатор организации")
    val organizationId: Long,
    @Schema(description = "Университет")
    val university: String,
    @Schema(description = "Факультет")
    val faculty: String,
    @Schema(description = "Название группы")
    val groupName: String,
    @Schema(description = "Мягкое удаление")
    val isSoftDeleted: Boolean
)
