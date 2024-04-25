package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.itech.sno_api.entity.ForumEntity
import ru.itech.sno_api.entity.ForumTopicEntity

@Schema(description = "Тема форума")
data class ForumTopicDTO(
    @Schema(description = "Идентификатор темы форума", example = "1")
    val topicId: Long,

    @Schema(description = "Идентификатор форума", example = "100")
    val forumId: Long,

    @Schema(description = "Название", example = "Обсуждение домашнего задания")
    val title: String,

    @Schema(description = "Описание", example = "Место для вопросов и ответов по последнему домашнему заданию")
    val description: String?
)
fun ForumTopicDTO.toEntity(): ForumTopicEntity {
    val topicEntity = ForumTopicEntity()
    topicEntity.topicId = this.topicId
    topicEntity.forum = ForumEntity().apply { this.forumId = this@toEntity.forumId }
    topicEntity.title = this.title
    topicEntity.description = this.description
    return topicEntity
}