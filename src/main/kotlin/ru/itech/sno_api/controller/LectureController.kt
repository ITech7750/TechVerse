package ru.itech.sno_api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import ru.itech.sno_api.dto.LectureDTO
import ru.itech.sno_api.service.LectureService
import java.time.LocalDate

@RestController
@RequestMapping("/api/v1/lectures")
@Tag(
    name = "Lecture API",
    description = "Управление лекциями и семинарами"
)
class LectureController(
    private val lectureService: LectureService
) {

    @Operation(summary = "Получение всех лекций")
    @GetMapping
    fun getAll(): List<LectureDTO> =
        lectureService.getAll()

    @Operation(summary = "Поиск лекций с фильтрацией и сортировкой")
    @GetMapping("/filtered-and-sorted")
    fun findAllFilteredAndSorted(
        @RequestParam("title") title: String?,
        @RequestParam("lecturerId") lecturerId: Long?,
        @RequestParam("startDate") startDate: LocalDate?,
        @RequestParam("endDate") endDate: LocalDate?,
        @RequestParam("sortBy") sortBy: String,
        @RequestParam("sortDirection") sortDirection: String
    ): List<LectureDTO> =
        lectureService.findAllFilteredAndSorted(title, lecturerId, startDate, endDate, sortBy, sortDirection)

    @Operation(summary = "Получение лекции по идентификатору")
    @GetMapping("/{lectureId}")
    fun getById(@PathVariable lectureId: Long): LectureDTO =
        lectureService.getById(lectureId)

    @Operation(summary = "Поиск лекций по названию")
    @GetMapping("/title/{title}")
    fun findByTitle(@PathVariable title: String): List<LectureDTO> =
        lectureService.findByTitle(title)

    @Operation(summary = "Поиск лекций по идентификатору лектора")
    @GetMapping("/lecturer/{lecturerId}")
    fun findByLecturer(@PathVariable lecturerId: Long): List<LectureDTO> =
        lectureService.findByLecturer(lecturerId)

    @Operation(summary = "Создание новой лекции")
    @PostMapping
    fun create(@RequestBody lecture: LectureDTO): LectureDTO =
        lectureService.create(lecture)

    @Operation(summary = "Обновление данных лекции")
    @PutMapping("/{lectureId}")
    fun update(@PathVariable lectureId: Long, @RequestBody lecture: LectureDTO): LectureDTO =
        lectureService.update(lectureId, lecture)

    @Operation(summary = "Удаление лекции")
    @DeleteMapping("/{lectureId}")
    fun delete(@PathVariable lectureId: Long) =
        lectureService.delete(lectureId)

    @Operation(summary = "Получение всех лекций постранично")
    @GetMapping("/paginated")
    fun getAllPaginated(@RequestParam("page") pageIndex: Int, @RequestParam("size") pageSize: Int): List<LectureDTO> =
        lectureService.getAllPaginated(pageIndex, pageSize)

    @Operation(summary = "Обновление файла лекции")
    @PatchMapping("/{lectureId}/file/{fileId}")
    fun updateFile(@PathVariable lectureId: Long, @PathVariable fileId: Long) {
        lectureService.updateFile(lectureId, fileId)
    }

    @Operation(summary = "Обновление курса лекции")
    @PatchMapping("/{lectureId}/course/{courseId}")
    fun updateCourse(@PathVariable lectureId: Long, @PathVariable courseId: Long) {
        lectureService.updateCourse(lectureId, courseId)
    }

    @Operation(summary = "Обновление лектора лекции")
    @PatchMapping("/{lectureId}/lecturer/{lecturerId}")
    fun updateLecturer(@PathVariable lectureId: Long, @PathVariable lecturerId: Long) {
        lectureService.updateLecturer(lectureId, lecturerId)
    }

    @Operation(summary = "Обновление описания лекции")
    @PatchMapping("/{lectureId}/description")
    fun updateDescription(@PathVariable lectureId: Long, @RequestParam description: String) {
        lectureService.updateDescription(lectureId, description)
    }

    @Operation(summary = "Обновление форума лекции")
    @PatchMapping("/{lectureId}/forum/{forumId}")
    fun updateForum(@PathVariable lectureId: Long, @PathVariable forumId: Long) {
        lectureService.updateForum(lectureId, forumId)
    }
}
