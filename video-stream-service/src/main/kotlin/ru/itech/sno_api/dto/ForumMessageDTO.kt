package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.itech.sno_api.entity.ForumMessageEntity
import ru.itech.sno_api.entity.ForumTopicEntity
import ru.itech.sno_api.entity.UserEntity
import java.sql.Timestamp

@Schema(description = "Сообщение форума")
data class ForumMessageDTO(
    @Schema(description = "Идентификатор сообщения форума", example = "100")
    val messageId: Long,

    @Schema(description = "Идентификатор темы форума", example = "50")
    val topicId: Long,

    @Schema(description = "Идентификатор пользователя", example = "1")
    val userId: Long,

    @Schema(description = "Идентификатор ответа", example = "99")
    val replyId: Long,

    @Schema(description = "Текст сообщения", example = "Привет, как дела?")
    val messageText: String,

    @Schema(description = "Временная метка", example = "2024-04-25T12:34:56.789")
    val timestamp: Timestamp
)
fun ForumMessageDTO.toEntity(): ForumMessageEntity {
    val messageEntity = ForumMessageEntity()
    messageEntity.messageId = this.messageId
    messageEntity.topic = ForumTopicEntity().apply { this.topicId = this@toEntity.topicId }
    messageEntity.user = UserEntity().apply { this.userId = this@toEntity.userId }
    messageEntity.replyId = this.replyId
    messageEntity.messageText = this.messageText
    messageEntity.timestamp = this.timestamp
    return messageEntity
}