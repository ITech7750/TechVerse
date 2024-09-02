package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.itech.sno_api.entity.ForumParticipantRoleEntity

@Schema(description = "Роль участника форума")
data class ForumParticipantRoleDTO(
    @Schema(description = "Идентификатор роли участника форума")
    val roleId: Long,
    @Schema(description = "Участник форума")
    val participant: ForumParticipantDTO,
    @Schema(description = "Название роли")
    val roleName: String
)
fun ForumParticipantRoleDTO.toEntity(): ForumParticipantRoleEntity {
    val entity = ForumParticipantRoleEntity()
    entity.roleId = this.roleId
    entity.roleName = this.roleName
    entity.participant = this.participant.toEntity() // Убедитесь, что participant DTO имеет метод toEntity()
    return entity
}