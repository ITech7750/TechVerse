package ru.itech.sno_api.service.implementation

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.itech.sno_api.dto.CourseDTO
import ru.itech.sno_api.dto.toEntity
import ru.itech.sno_api.entity.CourseEntity
import ru.itech.sno_api.entity.UserEntity
import ru.itech.sno_api.entity.toDTO
import ru.itech.sno_api.repository.CourseRepository
import ru.itech.sno_api.service.CourseService

@Service
@Transactional
open class CourseServiceImplementation(
    private val courseRepository: CourseRepository
) : CourseService {

    override fun findAll(): List<CourseDTO> =
        courseRepository.findAll().map(CourseEntity::toDTO)

    override fun findById(courseId: Long): CourseDTO =
        courseRepository.findById(courseId).orElseThrow {
            EntityNotFoundException("Course with ID $courseId not found")
        }.toDTO()

    override fun create(courseDTO: CourseDTO): CourseDTO =
        courseRepository.save(courseDTO.toEntity()).toDTO()

    override fun update(courseId: Long, courseDTO: CourseDTO): CourseDTO {
        val existingCourse = courseRepository.findById(courseId).orElseThrow {
            EntityNotFoundException("Course with ID $courseId not found")
        }
        existingCourse.title = courseDTO.title
        existingCourse.description = courseDTO.description
        existingCourse.startDate = courseDTO.startDate
        existingCourse.endDate = courseDTO.endDate
        existingCourse.admin = courseDTO.adminId?.let { UserEntity(userId = it) } // Assumes UserEntity fetch or creation is simplified

        return courseRepository.save(existingCourse).toDTO()
    }

    override fun delete(courseId: Long) {
        courseRepository.deleteById(courseId)
    }
}
