package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Тема форума")
data class ForumTopicDTO(
    @Schema(description = "Идентификатор темы форума")
    val topicId: Long,
    @Schema(description = "Форум")
    val forum: ForumDTO,
    @Schema(description = "Название")
    val title: String,
    @Schema(description = "Описание")
    val description: String
)
