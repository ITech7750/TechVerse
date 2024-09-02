package ru.itech.sno_api.dto


import ru.itech.sno_api.entity.UserCourseEntity
import ru.itech.sno_api.repository.CourseRepository
import ru.itech.sno_api.repository.UserRepository
import java.time.LocalDate

data class UserCourseDTO(
    val userId: Long = 1,

    var courseId: Long = 1,

    var registrationDate: LocalDate = LocalDate.now(),

    var completionStatus: String = "",

    /*
    var user: UserDTO? = null,

    var course: CourseDTO? = null

     */
)

fun UserCourseDTO.toEntity(
    userRepository: UserRepository,
    courseRepository: CourseRepository
): UserCourseEntity {
    val user = userRepository.findById(this.userId).orElseThrow {
        IllegalArgumentException("User with ID ${this.userId} not found")
    }
    val course = courseRepository.findById(this.courseId).orElseThrow {
        IllegalArgumentException("Course with ID ${this.courseId} not found")
    }

    return UserCourseEntity.create(user, course)
}



