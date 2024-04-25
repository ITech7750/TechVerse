package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Роль участника форума")
data class ForumParticipantRoleDTO(
    @Schema(description = "Идентификатор роли участника форума")
    val roleId: Long,
    @Schema(description = "Участник форума")
    val participant: ForumParticipantDTO,
    @Schema(description = "Название роли")
    val roleName: String
)
