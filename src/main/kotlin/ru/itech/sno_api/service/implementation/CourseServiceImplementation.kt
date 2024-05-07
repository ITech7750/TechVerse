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
import ru.itech.sno_api.repository.LectureRepository
import ru.itech.sno_api.repository.UserRepository
import ru.itech.sno_api.service.CourseService

@Service
@Transactional
open class CourseServiceImplementation(
    private val courseRepository: CourseRepository,
    private val userRepository: UserRepository,
    private val lectureRepository: LectureRepository,
) : CourseService {

    override fun findAll(): List<CourseDTO> =
        courseRepository.findAll().map(CourseEntity::toDTO)

    override fun findById(courseId: Long): CourseDTO =
        courseRepository.findById(courseId).orElseThrow {
            EntityNotFoundException("Course with ID $courseId not found")
        }.toDTO()

    override fun create(courseDTO: CourseDTO): CourseDTO {
        // Создаем объект CourseEntity из DTO и передаем userRepository для обработки связанных пользователей
        val courseEntity = courseDTO.toEntity(null, userRepository)
        // Сохраняем курс в репозитории и возвращаем его DTO представление
        return courseRepository.save(courseEntity).toDTO()
    }
    override fun findByTitle(title: String): List<CourseDTO> =
        courseRepository.findByTitle(title).map { it.toDTO() }

    override fun findByDescription(description: String): List<CourseDTO> =
        courseRepository.findByDescriptionContaining(description).map { it.toDTO() }

    override fun update(courseId: Long, courseDTO: CourseDTO): CourseDTO {
        val existingCourse = courseRepository.findById(courseId).orElseThrow {
            EntityNotFoundException("Course with ID $courseId not found")
        }

        existingCourse.apply {
            title = courseDTO.title
            description = courseDTO.description
            startDate = courseDTO.startDate
            endDate = courseDTO.endDate
            admin = courseDTO.adminId?.let { userRepository.findById(it).orElse(null) }

        }

        return courseRepository.save(existingCourse).toDTO()
    }


    override fun delete(courseId: Long) {
        courseRepository.deleteById(courseId)
    }
}
