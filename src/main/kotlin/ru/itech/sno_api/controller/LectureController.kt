package ru.itech.sno_api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import ru.itech.sno_api.dto.LectureDTO
import ru.itech.sno_api.service.LectureService

@RestController
@RequestMapping("/api/v1/lectures")
@Tag(
    name = "Lecture API",
    description = "Лекции"
)
class LectureController(
    private val lectureService: LectureService
) {

    @Operation(method = "Получение всех лекций")
    @GetMapping
    fun getAll(): List<LectureDTO> =
        lectureService.getAll()

    @Operation(method = "Получение лекции по идентификатору")
    @GetMapping("/{lectureId}")
    fun getById(@PathVariable lectureId: Long): LectureDTO =
        lectureService.getById(lectureId)

    @Operation(method = "Создание новой лекции")
    @PostMapping
    fun create(@RequestBody lecture: LectureDTO): LectureDTO =
        lectureService.create(lecture)

    @Operation(method = "Обновление данных лекции")
    @PutMapping("/{lectureId}")
    fun update(@PathVariable lectureId: Long, @RequestBody lecture: LectureDTO): LectureDTO =
        lectureService.update(lectureId, lecture)

    @Operation(method = "Удаление лекции")
    @DeleteMapping("/{lectureId}")
    fun delete(@PathVariable lectureId: Long) =
        lectureService.delete(lectureId)

    @Operation(method = "Получение всех лекций постранично")
    @GetMapping("/paginated")
    fun getAllPaginated(@RequestParam("page") pageIndex: Int, @RequestParam("size") pageSize: Int): List<LectureDTO> =
        lectureService.getAllPaginated(pageIndex, pageSize)
}
