package ru.itech.sno_api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import ru.itech.sno_api.dto.SummaryDTO
import ru.itech.sno_api.service.SummaryService

@RestController
@RequestMapping("/api/v1/summaries")
@Tag(
    name = "Summary API",
    description = "Управление конспектами"
)
class SummaryController(
    private val summaryService: SummaryService
) {

    @Operation(method = "Получение всех конспектов")
    @GetMapping
    fun getAll(): List<SummaryDTO> =
        summaryService.getAll()

    @Operation(method = "Получение конспекта по идентификатору")
    @GetMapping("/{summaryId}")
    fun getById(@PathVariable summaryId: Long): SummaryDTO =
        summaryService.getById(summaryId)

    @Operation(method = "Создание нового конспекта")
    @PostMapping
    fun create(@RequestBody summary: SummaryDTO): SummaryDTO =
        summaryService.create(summary)

    @Operation(method = "Обновление данных конспекта")
    @PutMapping("/{summaryId}")
    fun update(@PathVariable summaryId: Long, @RequestBody summary: SummaryDTO): SummaryDTO =
        summaryService.update(summaryId, summary)

    @Operation(method = "Удаление конспекта")
    @DeleteMapping("/{summaryId}")
    fun delete(@PathVariable summaryId: Long) =
        summaryService.delete(summaryId)
}
