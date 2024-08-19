package ru.itech.sno_api.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.itech.sno_api.dto.CourseDTO
import ru.itech.sno_api.service.CourseService
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/api/courses")
@Tag(
    name = "Course API",
    description = "Управление курсами"
)
class CourseController(
    private val courseService: CourseService
) {

    @GetMapping
    fun getAllCourses(): ResponseEntity<List<CourseDTO>> =
        ResponseEntity.ok(courseService.findAll())

    @GetMapping("/{id}")
    fun getCourseById(@PathVariable("id") courseId: Long): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.findById(courseId))

    @GetMapping("/title/{title}")
    fun getCoursesByTitle(@PathVariable title: String): ResponseEntity<List<CourseDTO>> =
        ResponseEntity.ok(courseService.findByTitle(title))


    @GetMapping("/description")
    fun getCoursesByDescription(@RequestParam description: String): ResponseEntity<List<CourseDTO>> =
        ResponseEntity.ok(courseService.findByDescription(description))

    @PostMapping
    fun createCourse(@RequestBody courseDTO: CourseDTO): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.create(courseDTO))

    @PutMapping("/{id}")
    fun updateCourse(@PathVariable("id") courseId: Long, @RequestBody courseDTO: CourseDTO): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.update(courseId, courseDTO))

    @DeleteMapping("/{id}")
    fun deleteCourse(@PathVariable("id") courseId: Long): ResponseEntity<Void> {
        courseService.delete(courseId)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{courseId}/title")
    fun updateTitle(@PathVariable courseId: Long, @RequestBody newTitle: String): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.updateTitle(courseId, newTitle))

    @PatchMapping("/{courseId}/description")
    fun updateDescription(@PathVariable courseId: Long, @RequestBody newDescription: String): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.updateDescription(courseId, newDescription))

    @PatchMapping("/{courseId}/admin")
    fun updateAdmin(@PathVariable courseId: Long, @RequestBody newAdminId: Long): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.updateAdmin(courseId, newAdminId))

    @PatchMapping("/{courseId}/startDate")
    fun updateStartDate(@PathVariable courseId: Long, @RequestBody newStartDate: LocalDate): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.updateStartDate(courseId, newStartDate))

    @PatchMapping("/{courseId}/endDate")
    fun updateEndDate(@PathVariable courseId: Long, @RequestBody newEndDate: LocalDate): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.updateEndDate(courseId, newEndDate))

    // Добавление пользователя в курс
    @PatchMapping("/{courseId}/addUser/{userId}")
    fun addUserToCourse(@PathVariable courseId: Long, @PathVariable userId: Long): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.addUserToCourse(courseId, userId))

    // Добавление лекции в курс
    @PatchMapping("/{courseId}/addLecture/{lectureId}")
    fun addLectureToCourse(@PathVariable courseId: Long, @PathVariable lectureId: Long): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.addLectureToCourse(courseId, lectureId))


    // Удаление пользователя из курса
    @DeleteMapping("/{courseId}/user/{userId}")
    fun removeUser(@PathVariable courseId: Long, @PathVariable userId: Long): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.removeUserFromCourse(courseId, userId))

    // Удаление лекции из курса
    @DeleteMapping("/{courseId}/lecture/{lectureId}")
    fun removeLecture(@PathVariable courseId: Long, @PathVariable lectureId: Long): ResponseEntity<CourseDTO> =
        ResponseEntity.ok(courseService.removeLectureFromCourse(courseId, lectureId))

}
