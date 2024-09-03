package ru.itech.sno_api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.itech.sno_api.dto.CourseDTO
import ru.itech.sno_api.service.CourseService
import java.time.LocalDate

@RestController
@RequestMapping("/api/courses")
@Tag(
    name = "Course API",
    description = "Управление курсами"
)
class CourseController(
    private val courseService: CourseService
) {

    @Operation(summary = "Получение всех курсов")
    @GetMapping("/list")
    fun getAllCourses(): ResponseEntity<List<CourseDTO>> =
        ResponseEntity.ok(courseService.findAll())

    @Operation(
        summary = "Поиск курсов с фильтрацией и сортировкой",
        parameters = [
            Parameter(name = "title", description = "Название курса", required = false),
            Parameter(name = "description", description = "Описание курса", required = false),
            Parameter(name = "startDate", description = "Дата начала курса", required = false),
            Parameter(name = "endDate", description = "Дата окончания курса", required = false),
            Parameter(name = "sortBy", description = "Поле для сортировки (по умолчанию: title)", required = false),
            Parameter(name = "sortDirection", description = "Направление сортировки (asc или desc, по умолчанию: asc)", required = false)
        ]
    )
    @GetMapping("/filtered-and-sorted")
    fun getAllCourses(
        @RequestParam(required = false) title: String?,
        @RequestParam(required = false) description: String?,
        @RequestParam(required = false) startDate: LocalDate?,
        @RequestParam(required = false) endDate: LocalDate?,
        @RequestParam(required = false, defaultValue = "title") sortBy: String,
        @RequestParam(required = false, defaultValue = "asc") sortDirection: String
    ): ResponseEntity<List<CourseDTO>> =
        ResponseEntity.ok(courseService.findAllFilteredAndSorted(title, description, startDate, endDate, sortBy, sortDirection))

    @Operation(summary = "Получение курса по идентификатору")
    @GetMapping("/{id}")
    fun getCourseById(@PathVariable("id") courseId: Long): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.findById(courseId))

    @Operation(summary = "Поиск курсов по названию")
    @GetMapping("/title/{title}")
    fun getCoursesByTitle(@PathVariable title: String): ResponseEntity<List<CourseDTO>> =
        ResponseEntity.ok(courseService.findByTitle(title))

    @Operation(summary = "Поиск курсов по описанию")
    @GetMapping("/description")
    fun getCoursesByDescription(@RequestParam description: String): ResponseEntity<List<CourseDTO>> =
        ResponseEntity.ok(courseService.findByDescription(description))

    @Operation(summary = "Создание нового курса")
    @PostMapping
    fun createCourse(@RequestBody courseDTO: CourseDTO): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.create(courseDTO))

    @Operation(summary = "Обновление данных курса")
    @PutMapping("/{id}")
    fun updateCourse(@PathVariable("id") courseId: Long, @RequestBody courseDTO: CourseDTO): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.update(courseId, courseDTO))

    @Operation(summary = "Удаление курса")
    @DeleteMapping("/{id}")
    fun deleteCourse(@PathVariable("id") courseId: Long): ResponseEntity<Void> {
        courseService.delete(courseId)
        return ResponseEntity.noContent().build()
    }

    @Operation(summary = "Обновление названия курса")
    @PatchMapping("/{courseId}/title")
    fun updateTitle(@PathVariable courseId: Long, @RequestBody newTitle: String): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.updateTitle(courseId, newTitle))

    @Operation(summary = "Обновление описания курса")
    @PatchMapping("/{courseId}/description")
    fun updateDescription(@PathVariable courseId: Long, @RequestBody newDescription: String): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.updateDescription(courseId, newDescription))

    @Operation(summary = "Обновление администратора курса")
    @PatchMapping("/{courseId}/admin")
    fun updateAdmin(@PathVariable courseId: Long, @RequestBody newAdminId: Long): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.updateAdmin(courseId, newAdminId))

    @Operation(summary = "Обновление даты начала курса")
    @PatchMapping("/{courseId}/startDate")
    fun updateStartDate(@PathVariable courseId: Long, @RequestBody newStartDate: LocalDate): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.updateStartDate(courseId, newStartDate))

    @Operation(summary = "Обновление даты окончания курса")
    @PatchMapping("/{courseId}/endDate")
    fun updateEndDate(@PathVariable courseId: Long, @RequestBody newEndDate: LocalDate): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.updateEndDate(courseId, newEndDate))

    @Operation(summary = "Добавление пользователя в курс")
    @PatchMapping("/{courseId}/addUser/{userId}")
    fun addUserToCourse(@PathVariable courseId: Long, @PathVariable userId: Long): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.addUserToCourse(courseId, userId))

    @Operation(summary = "Добавление лекции в курс")
    @PatchMapping("/{courseId}/addLecture/{lectureId}")
    fun addLectureToCourse(@PathVariable courseId: Long, @PathVariable lectureId: Long): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.addLectureToCourse(courseId, lectureId))

    @Operation(summary = "Удаление пользователя из курса")
    @DeleteMapping("/{courseId}/user/{userId}")
    fun removeUser(@PathVariable courseId: Long, @PathVariable userId: Long): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.removeUserFromCourse(courseId, userId))

    @Operation(summary = "Удаление лекции из курса")
    @DeleteMapping("/{courseId}/lecture/{lectureId}")
    fun removeLecture(@PathVariable courseId: Long, @PathVariable lectureId: Long): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.removeLectureFromCourse(courseId, lectureId))
}
