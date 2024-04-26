package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.transaction.Transactional
import ru.itech.sno_api.entity.CourseEntity
import ru.itech.sno_api.repository.LectureRepository
import ru.itech.sno_api.repository.UserRepository
import java.util.Date

@Schema(description = "Данные курса")
data class CourseDTO(
    @Schema(description = "Идентификатор курса", example = "1", required = true)
    val courseId: Long,

    @Schema(description = "Название курса", example = "Математика")
    val title: String,

    @Schema(description = "Описание курса", example = "Курс по математике для студентов")
    val description: String,

    @Schema(description = "Дата начала курса", type = "string", format = "date", example = "2022-01-01")
    val startDate: Date?,

    @Schema(description = "Дата окончания курса", type = "string", format = "date", example = "2022-12-31")
    val endDate: Date?,

    @Schema(description = "Идентификатор администратора курса", example = "10")
    val adminId: Long?,

    @Schema(description = "Список идентификаторов пользователей, зарегистрированных на курс")
    val userIds: Set<Long> = emptySet(),

    @Schema(description = "Список идентификаторов лекций, входящих в курс")
    val lectureIds: Set<Long> = emptySet()
) {
    constructor(course: CourseEntity) : this(
        courseId = course.courseId,
        title = course.title,
        description = course.description,
        startDate = course.startDate,
        endDate = course.endDate,
        adminId = course.admin?.userId,
        userIds = course.users.map { it.userId }.toSet(),
        lectureIds = course.lectures.map { it.lectureId }.toSet()
    )
}

@Transactional
fun CourseDTO.toEntity(
    existingCourse: CourseEntity? = null,
    userRepository: UserRepository,
): CourseEntity {
    val course = existingCourse ?: CourseEntity()
    course.courseId = this.courseId
    course.title = this.title
    course.description = this.description
    course.startDate = this.startDate
    course.endDate = this.endDate
    course.admin = this.adminId?.let { userRepository.findById(it).orElse(null) }
    return course
}
