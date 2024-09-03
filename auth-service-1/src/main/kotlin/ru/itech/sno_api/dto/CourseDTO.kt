package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.itech.sno_api.entity.CourseEntity
import ru.itech.sno_api.entity.CourseLectureEntity
import ru.itech.sno_api.entity.UserCourseEntity
import ru.itech.sno_api.repository.LectureRepository
import ru.itech.sno_api.repository.UserRepository
import java.time.LocalDate

@Schema(description = "Данные курса")
data class CourseDTO(
    @Schema(description = "Идентификатор курса", example = "1", required = true)
    val courseId: Long,

    @Schema(description = "Название курса", example = "Математика")
    val title: String,

    @Schema(description = "Описание курса", example = "Курс по математике для студентов")
    val description: String,

    @Schema(description = "Дата начала курса", type = "string", format = "date", example = "2022-01-01")
    val startDate: LocalDate?,

    @Schema(description = "Дата окончания курса", type = "string", format = "date", example = "2022-12-31")
    val endDate: LocalDate?,

    @Schema(description = "Идентификатор администратора курса", example = "10")
    val adminId: Long?,

    @Schema(description = "Список идентификаторов пользователей, зарегистрированных на курс")
    val userIds: Set<Long>,

    @Schema(description = "Список идентификаторов лекций, входящих в курс")
    val lectureIds: Set<Long>
) {
    constructor(course: CourseEntity) : this(
        courseId = course.courseId,
        title = course.title,
        description = course.description,
        startDate = course.startDate,
        endDate = course.endDate,
        adminId = course.admin?.userId,
        userIds = course.userCourses.mapNotNull { it.user?.userId }.toSet(),
        lectureIds = course.courseLectures.map { it.lecture?.lectureId ?: 0 }.toSet() // Use course.courseLectures here
    )
}

fun CourseDTO.toEntity(
    existingCourse: CourseEntity? = null,
    userRepository: UserRepository,
    lectureRepository: LectureRepository
): CourseEntity {
    val course = existingCourse ?: CourseEntity.create(
        title = this.title,
        description = this.description,
        startDate = this.startDate,
        endDate = this.endDate,
        admin = this.adminId?.let { userRepository.findById(it).orElse(null) }
    )

    course.courseId = this.courseId


    course.userCourses = this.userIds.mapNotNull { userId ->
        userRepository.findById(userId).orElse(null)?.let { user ->
            UserCourseEntity.create(user, course)
        }
    }.toMutableSet()

    course.courseLectures = lectureRepository.findAllById(this.lectureIds).mapIndexed { index, lecture ->
        CourseLectureEntity.create(
            course = course,
            lecture = lecture,
            lectureOrder = index + 1
        )
    }.toMutableSet()

    return course
}
