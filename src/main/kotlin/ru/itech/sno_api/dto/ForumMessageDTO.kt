package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.sql.Timestamp

@Schema(description = "Сообщение форума")
data class ForumMessageDTO(
    @Schema(description = "Идентификатор сообщения форума")
    val messageId: Long,
    @Schema(description = "Тема форума")
    val topic: ForumTopicDTO,
    @Schema(description = "Пользователь")
    val user: UserInfoDTO,
    @Schema(description = "Идентификатор ответа")
    val replyId: Long,
    @Schema(description = "Текст сообщения")
    val messageText: String,
    @Schema(description = "Временная метка")
    val timestamp: Timestamp
)
