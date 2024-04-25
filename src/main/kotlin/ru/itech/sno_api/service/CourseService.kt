package ru.itech.sno_api.service


import ru.itech.sno_api.dto.CourseDTO

interface CourseService {
    fun findAll(): List<CourseDTO>
    fun findById(courseId: Long): CourseDTO
    fun create(courseDTO: CourseDTO): CourseDTO
    fun update(courseId: Long, courseDTO: CourseDTO): CourseDTO
    fun delete(courseId: Long)
}
