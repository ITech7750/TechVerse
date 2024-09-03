package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.itech.sno_api.entity.FilesEntity

@Schema(description = "Файл")
data class FilesDTO(
    @Schema(description = "Идентификатор файла")
    val fileId: Long?,

    @Schema(description = "Путь к файлу")
    val filePath: String,

    @Schema(description = "Тип файла")
    val fileType: String?
) {
    fun toEntity() = FilesEntity(
        fileId = fileId,
        filePath = filePath,
        fileType = fileType ?: "" // assuming fileType should not be null in the entity
    )
}
