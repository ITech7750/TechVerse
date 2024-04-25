package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.itech.sno_api.entity.ForumEntity

@Schema(description = "Форум")
data class ForumDTO(
    @Schema(description = "Идентификатор форума")
    val forumId: Long,
    @Schema(description = "Название")
    val title: String,
    @Schema(description = "Описание")
    val description: String
)

fun ForumDTO.toEntity(): ForumEntity {
    return ForumEntity(
        forumId = forumId,
        title = title,
        description = description
    )
}