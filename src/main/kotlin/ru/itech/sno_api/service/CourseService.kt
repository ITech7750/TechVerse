package ru.itech.sno_api.service


import ru.itech.sno_api.dto.CourseDTO
import java.time.LocalDate
import java.util.*

interface CourseService {
    fun findAll(): List<CourseDTO>
    fun findById(courseId: Long): CourseDTO
    fun create(courseDTO: CourseDTO): CourseDTO
    fun update(courseId: Long, courseDTO: CourseDTO): CourseDTO
    fun delete(courseId: Long)
    fun findByTitle(title: String): List<CourseDTO>
    fun findByDescription(description: String): List<CourseDTO>
    fun updateTitle(courseId: Long, newTitle: String): CourseDTO
    fun updateDescription(courseId: Long, newDescription: String): CourseDTO
    fun updateAdmin(courseId: Long, newAdminId: Long): CourseDTO
    fun updateStartDate(courseId: Long, newStartDate: LocalDate): CourseDTO
    fun updateEndDate(courseId: Long, newEndDate: LocalDate): CourseDTO
    fun removeUserFromCourse(courseId: Long, userId: Long): CourseDTO
    fun removeLectureFromCourse(courseId: Long, lectureId: Long): CourseDTO
    fun addUserToCourse(courseId: Long, userId: Long): CourseDTO
    fun addLectureToCourse(courseId: Long, lectureId: Long): CourseDTO
}
