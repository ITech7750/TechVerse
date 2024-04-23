package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.itech.sno_api.entity.LectureEntity
import ru.itech.sno_api.entity.toDTO
import ru.itech.sno_api.service.implementation.toDTO
import java.util.*


@Schema(description = "Лекция")
data class LectureDTO(
    @Schema(description = "Идентификатор лекции")
    val lectureId: Long,
    @Schema(description = "Лектор")
    val lecturer: UserInfoDTO,
    @Schema(description = "Название")
    val title: String,
    @Schema(description = "Описание")
    val description: String,
    @Schema(description = "Дата")
    val date: Date,
    @Schema(description = "Конспект")
    val summary: SummaryDTO,
    @Schema(description = "Форум")
    val forum: ForumDTO,
    val file: FilesDTO
)
fun LectureEntity.toDTO(): LectureDTO {
    return LectureDTO(
        lectureId = lectureId,
        lecturer = lecturer.toDTO(),
        title = title,
        description = description,
        date = date!!,
        summary = summary.toDTO(),
        forum = forum.toDTO(),
        file = file.toDTO()

    )
}

