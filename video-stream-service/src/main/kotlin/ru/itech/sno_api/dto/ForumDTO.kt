package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.itech.sno_api.entity.ForumEntity

@Schema(description = "Форум")
data class ForumDTO(
    @Schema(description = "Идентификатор форума", example = "1")
    val forumId: Long,

    @Schema(description = "Название", example = "Обсуждение лекции по математике")
    val title: String,

    @Schema(description = "Описание", example = "Тут можно обсудить задачи и теории, рассмотренные на лекции")
    val description: String,

    @Schema(description = "Идентификатор связанной лекции", example = "404")
    val lectureId: Long?
)

fun ForumDTO.toEntity(): ForumEntity {
    val entity = ForumEntity(
        forumId = forumId,
        title = title,
        description = description
    )
    return entity
}
