package ru.itech.sno_api.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.itech.sno_api.dto.CourseDTO
import ru.itech.sno_api.service.CourseService

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
}
