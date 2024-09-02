package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.itech.sno_api.entity.SummaryEntity

@Schema(description = "Конспект")
data class SummaryDTO(
    @Schema(description = "Идентификатор конспекта")
    val summaryId: Long,
    @Schema(description = "Название")
    val title: String,
    @Schema(description = "Описание")
    val description: String
)

fun SummaryDTO.toEntity(existingSummary: SummaryEntity? = null): SummaryEntity {
    val summary = existingSummary ?: SummaryEntity()
    summary.summaryId = this.summaryId
    summary.title = this.title
    summary.description = this.description
    return summary
}