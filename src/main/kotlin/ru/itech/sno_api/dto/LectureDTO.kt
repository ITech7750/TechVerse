package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.itech.sno_api.entity.LectureEntity
import ru.itech.sno_api.service.implementation.toEntity
import java.util.*

@Schema(description = "Лекция")
data class LectureDTO(
    @Schema(description = "Идентификатор лекции", example = "1")
    val lectureId: Long,

    @Schema(description = "Лектор", nullable = true) // Make nullable if applicable
    val lecturer: UserInfoDTO?,

    @Schema(description = "Название", example = "Введение в информатику")
    val title: String,

    @Schema(description = "Описание", example = "Описание курса информатики.")
    val description: String,

    @Schema(description = "Дата", nullable = true, type = "string", format = "date-time", example = "2022-12-01T15:00:00Z")
    val date: Date?,

    @Schema(description = "Конспект", nullable = true)
    val summary: SummaryDTO?,

    @Schema(description = "Форум", nullable = true)
    val forum: ForumDTO?,

    @Schema(description = "Файл", nullable = true)
    val file: FilesDTO?
)
fun LectureDTO.toEntity(): LectureEntity {
    val lecture = LectureEntity()
    lecture.lectureId = this.lectureId
    lecture.title = this.title
    lecture.description = this.description
    lecture.date = this.date
    lecture.lecturer = this.lecturer?.toEntity() ?: null
    lecture.summary = this.summary?.toEntity() ?: null
    lecture.forum = this.forum?.toEntity() ?: null
    lecture.file = this.file?.toEntity() ?: null

    return lecture
}