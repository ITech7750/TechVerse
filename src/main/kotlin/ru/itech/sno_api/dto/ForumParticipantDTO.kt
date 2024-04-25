package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.itech.sno_api.entity.ForumEntity
import ru.itech.sno_api.entity.ForumParticipantEntity
import ru.itech.sno_api.entity.UserInfoEntity

@Schema(description = "Участник форума")
data class ForumParticipantDTO(
    @Schema(description = "Идентификатор участника форума", example = "1")
    val participantId: Long,

    @Schema(description = "Идентификатор форума", example = "100")
    val forumId: Long,

    @Schema(description = "Идентификатор пользователя", example = "50")
    val userId: Long
)
fun ForumParticipantDTO.toEntity(): ForumParticipantEntity {
    val participant = ForumParticipantEntity()
    participant.participantId = this.participantId
    participant.forum = ForumEntity().apply { this.forumId = this@toEntity.forumId } // Ideally, fetch from DB
    participant.user = UserInfoEntity().apply { this.userId = this@toEntity.userId } // Ideally, fetch from DB
    return participant
}

