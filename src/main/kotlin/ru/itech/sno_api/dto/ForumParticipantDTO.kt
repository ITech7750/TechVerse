package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Участник форума")
data class ForumParticipantDTO(
    @Schema(description = "Идентификатор участника форума")
    val participantId: Long,
    @Schema(description = "Форум")
    val forum: ForumDTO,
    @Schema(description = "Пользователь")
    val user: UserInfoDTO
)