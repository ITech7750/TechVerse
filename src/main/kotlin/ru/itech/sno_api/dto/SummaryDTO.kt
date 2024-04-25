package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Конспект")
data class SummaryDTO(
    @Schema(description = "Идентификатор конспекта")
    val summaryId: Long,
    @Schema(description = "Название")
    val title: String,
    @Schema(description = "Описание")
    val description: String
)