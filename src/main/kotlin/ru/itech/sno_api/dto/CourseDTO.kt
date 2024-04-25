package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.itech.sno_api.entity.CourseEntity
import ru.itech.sno_api.entity.UserEntity
import java.util.Date

@Schema(description = "Объект передачи данных для курса")
data class CourseDTO(
    @Schema(description = "Иднтификатор курса", example = "1", required = true)
    val courseId: Long,

    @Schema(description = "Название курса", example = "ЭВМ")
    val title: String,

    @Schema(description = "Подробное описание курса", example = "ЭВМ И ПУ")
    val description: String,

    @Schema(description = "Дата начала курса", type = "string", format = "date", example = "2022-01-01")
    val startDate: Date?,

    @Schema(description = "Дата окончания курса", type = "string", format = "date", example = "2022-12-31")
    val endDate: Date?,

    @Schema(description = "ID пользователя, являющегося администратором курса", example = "22")
    val adminId: Long?
)

fun CourseDTO.toEntity(existingCourse: CourseEntity? = null): CourseEntity {
    val course = existingCourse ?: CourseEntity()
    course.courseId = this.courseId
    course.title = this.title
    course.description = this.description
    course.startDate = this.startDate
    course.endDate = this.endDate
    course.admin = if (this.adminId != null) UserEntity(userId = this.adminId) else null
    return course
}