package ru.itech.sno_api.service.implementation

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.itech.sno_api.core.domain.CourseSpecification
import ru.itech.sno_api.dto.CourseDTO
import ru.itech.sno_api.entity.*
import ru.itech.sno_api.repository.CourseRepository
import ru.itech.sno_api.repository.LectureRepository
import ru.itech.sno_api.repository.UserRepository
import ru.itech.sno_api.service.CourseService
import java.time.LocalDate

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

        val courseEntity = CourseEntity.create(
            title = courseDTO.title,
            description = courseDTO.description,
            startDate = courseDTO.startDate,
            endDate = courseDTO.endDate,
            admin = courseDTO.adminId?.let { userRepository.findById(it).orElse(null) }
        )

        val savedCourseEntity = courseRepository.save(courseEntity)


        val userCourses = courseDTO.userIds.mapNotNull { userId ->
            userRepository.findById(userId).orElse(null)?.let { user ->
                UserCourseEntity.create(user, savedCourseEntity)
            }
        }.toMutableSet()


        val courseLectures = lectureRepository.findAllById(courseDTO.lectureIds).mapIndexed { index, lecture ->
            CourseLectureEntity.create(
                course = savedCourseEntity,
                lecture = lecture,
                lectureOrder = index + 1
            )

        }.toMutableSet()


        savedCourseEntity.apply {
            this.userCourses = userCourses
            this.courseLectures = courseLectures
        }


        val updatedCourseEntity = courseRepository.save(savedCourseEntity)

        return updatedCourseEntity.toDTO()
    }

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


            userCourses.clear()
            userCourses.addAll(courseDTO.userIds.mapNotNull { userId ->
                userRepository.findById(userId).orElse(null)?.let { user ->
                    UserCourseEntity.create(user, this)
                }
            })
            courseLectures.clear()
            courseLectures.addAll(lectureRepository.findAllById(courseDTO.lectureIds).mapIndexed { index, lecture ->
                CourseLectureEntity.create(
                    course = this,
                    lecture = lecture,
                    lectureOrder = index + 1
                )
            }) }

        return courseRepository.save(existingCourse).toDTO()
    }

    override fun findByTitle(title: String): List<CourseDTO> =
        courseRepository.findByTitle(title).map { it.toDTO() }

    override fun findByDescription(description: String): List<CourseDTO> =
        courseRepository.findByDescriptionContaining(description).map { it.toDTO() }

    override fun delete(courseId: Long) {
        courseRepository.deleteById(courseId)
    }
    override fun updateTitle(courseId: Long, newTitle: String): CourseDTO {
        val course = courseRepository.findById(courseId).orElseThrow {
            EntityNotFoundException("Course with ID $courseId not found")
        }
        course.title = newTitle
        return courseRepository.save(course).toDTO()
    }

    override fun updateDescription(courseId: Long, newDescription: String): CourseDTO {
        val course = courseRepository.findById(courseId).orElseThrow {
            EntityNotFoundException("Course with ID $courseId not found")
        }
        course.description = newDescription
        return courseRepository.save(course).toDTO()
    }

    override fun updateAdmin(courseId: Long, newAdminId: Long): CourseDTO {
        val course = courseRepository.findById(courseId).orElseThrow {
            EntityNotFoundException("Course with ID $courseId not found")
        }
        val newAdmin = userRepository.findById(newAdminId).orElseThrow {
            EntityNotFoundException("Admin with ID $newAdminId not found")
        }
        course.admin = newAdmin
        return courseRepository.save(course).toDTO()
    }

    override fun updateStartDate(courseId: Long, newStartDate: LocalDate): CourseDTO {
        val course = courseRepository.findById(courseId).orElseThrow {
            EntityNotFoundException("Course with ID $courseId not found")
        }
        course.startDate = newStartDate
        return courseRepository.save(course).toDTO()
    }

    override fun updateEndDate(courseId: Long, newEndDate: LocalDate): CourseDTO {
        val course = courseRepository.findById(courseId).orElseThrow {
            EntityNotFoundException("Course with ID $courseId not found")
        }
        course.endDate = newEndDate
        return courseRepository.save(course).toDTO()
    }

    override fun removeUserFromCourse(courseId: Long, userId: Long): CourseDTO {
        val course = courseRepository.findById(courseId).orElseThrow {
            EntityNotFoundException("Course with ID $courseId not found")
        }
        val userCourse = course.userCourses.find { it.user?.userId == userId }
        if (userCourse != null) {
            course.userCourses.remove(userCourse)
            courseRepository.save(course)
        }
        return course.toDTO()
    }

    override fun removeLectureFromCourse(courseId: Long, lectureId: Long): CourseDTO {
        val course = courseRepository.findById(courseId).orElseThrow {
            EntityNotFoundException("Course with ID $courseId not found")
        }
        val courseLecture = course.courseLectures.find { it.lecture?.lectureId == lectureId }
        if (courseLecture != null) {
            course.courseLectures.remove(courseLecture)
            courseRepository.save(course)
        }
        return course.toDTO()
    }
    // Добавление пользоватля в курс
    override fun addUserToCourse(courseId: Long, userId: Long): CourseDTO {
        val course = courseRepository.findById(courseId).orElseThrow {
            EntityNotFoundException("Course with ID $courseId not found")
        }
        val user = userRepository.findById(userId).orElseThrow {
            EntityNotFoundException("User with ID $userId not found")
        }
        val userCourse = UserCourseEntity.create(user, course)
        if (!course.userCourses.contains(userCourse)) {
            course.userCourses.add(userCourse)
            courseRepository.save(course)
        }
        return course.toDTO()
    }

    // Добавление лекции в курс
    override fun addLectureToCourse(courseId: Long, lectureId: Long): CourseDTO {
        val course = courseRepository.findById(courseId).orElseThrow {
            EntityNotFoundException("Course with ID $courseId not found")
        }
        val lecture = lectureRepository.findById(lectureId).orElseThrow {
            EntityNotFoundException("Lecture with ID $lectureId not found")
        }
        val courseLecture = CourseLectureEntity.create(
            course = course,
            lecture = lecture,
            lectureOrder = course.courseLectures.size + 1
        )
        if (!course.courseLectures.contains(courseLecture)) {
            course.courseLectures.add(courseLecture)
            courseRepository.save(course)
        }
        return course.toDTO()
    }
    override fun findAllFilteredAndSorted(
        title: String?,
        description: String?,
        startDate: LocalDate?,
        endDate: LocalDate?,
        sortBy: String,
        sortDirection: String
    ): List<CourseDTO> {
        val courses = courseRepository.findAll(CourseSpecification(title, description, startDate, endDate))


        // Сортировка по указанному полю
        return courses.sortedWith(
            when (sortBy) {
                "title" -> if (sortDirection == "asc") compareBy { it.title } else compareByDescending { it.title }
                "startDate" -> if (sortDirection == "asc") compareBy { it.startDate } else compareByDescending { it.startDate }
                "endDate" -> if (sortDirection == "asc") compareBy { it.endDate } else compareByDescending { it.endDate }
                else -> compareBy { it.title } // Сортировка по умолчанию
            }
        ).map { it.toDTO() }
    }

}
