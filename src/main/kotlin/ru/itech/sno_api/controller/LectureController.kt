package ru.itech.sno_api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import ru.itech.sno_api.dto.LectureDTO
import ru.itech.sno_api.service.LectureService

@RestController
@RequestMapping("/api/v1/lectures")
@Tag(
    name = "Lecture API",
    description = "Управление лекциями и семинарами"
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

    @GetMapping("/title/{title}")
    fun findByTitle(@PathVariable title: String): List<LectureDTO> =
        lectureService.findByTitle(title)

    @GetMapping("/lecturer/{lecturerId}")
    fun findByLecturer(@PathVariable lecturerId: Long): List<LectureDTO> =
        lectureService.findByLecturer(lecturerId)

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

    @Operation(method = "Обновление файла лекции")
    @PatchMapping("/{lectureId}/file/{fileId}")
    fun updateFile(@PathVariable lectureId: Long, @PathVariable fileId: Long) {
        lectureService.updateFile(lectureId, fileId)
    }

    @Operation(method = "Обновление курса лекции")
    @PatchMapping("/{lectureId}/course/{courseId}")
    fun updateCourse(@PathVariable lectureId: Long, @PathVariable courseId: Long) {
        lectureService.updateCourse(lectureId, courseId)
    }

    @Operation(method = "Обновление лектора лекции")
    @PatchMapping("/{lectureId}/lecturer/{lecturerId}")
    fun updateLecturer(@PathVariable lectureId: Long, @PathVariable lecturerId: Long) {
        lectureService.updateLecturer(lectureId, lecturerId)
    }

    @Operation(method = "Обновление описания лекции")
    @PatchMapping("/{lectureId}/description")
    fun updateDescription(@PathVariable lectureId: Long, @RequestParam description: String) {
        lectureService.updateDescription(lectureId, description)
    }

    @Operation(method = "Обновление форума лекции")
    @PatchMapping("/{lectureId}/forum/{forumId}")
    fun updateForum(@PathVariable lectureId: Long, @PathVariable forumId: Long) {
        lectureService.updateForum(lectureId, forumId)
    }
}
