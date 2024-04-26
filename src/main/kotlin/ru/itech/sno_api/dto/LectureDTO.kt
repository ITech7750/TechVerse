package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.transaction.Transactional
import ru.itech.sno_api.entity.CourseEntity
import ru.itech.sno_api.entity.LectureEntity
import ru.itech.sno_api.repository.CourseRepository
import ru.itech.sno_api.service.implementation.toEntity
import java.util.*

@Schema(description = "Лекция")
data class LectureDTO(
    @Schema(description = "Идентификатор лекции", example = "1")
    val lectureId: Long,

    @Schema(description = "Лектор", nullable = true)
    val lecturer: UserDTO?,

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

@Transactional
fun LectureDTO.toEntity(courseRepository: CourseRepository): LectureEntity {
    val lecture = LectureEntity().apply {
        lectureId = this@toEntity.lectureId
        title = this@toEntity.title
        description = this@toEntity.description
        date = this@toEntity.date
        lecturer = this@toEntity.lecturer?.toEntity(courseRepository) ?: null
        summary = this@toEntity.summary?.toEntity() ?: null
        forum = this@toEntity.forum?.toEntity() ?: null
        file = this@toEntity.file?.toEntity() ?: null

    }

    return lecture
}