
package ru.itech.sno_api.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.transaction.Transactional
import ru.itech.sno_api.entity.OrganizationEntity
import ru.itech.sno_api.dto.CourseDTO
import ru.itech.sno_api.entity.CourseEntity
import ru.itech.sno_api.entity.UserCourseEntity
import ru.itech.sno_api.entity.UserEntity
import ru.itech.sno_api.repository.CourseRepository

@Schema(description = "Полная информация о пользователе")
data class UserDTO(
    @Schema(description = "Логин пользователя", example = "john_doe")
    val login: String = "",

    @Schema(description = "Пароль для аутентификации пользователя", example = "secure_password")
    var password: String = "",

    @Schema(description = "Электронная почта пользователя", example = "john.doe@example.com")
    val email: String = "",

    @Schema(description = "Уникальный идентификатор пользователя", example = "1001")
    val userId: Long = 0,

    @Schema(description = "Имя пользователя", example = "John", required = false)
    val firstName: String? = null,

    @Schema(description = "Фамилия пользователя", example = "Doe", required = false)
    val lastName: String? = null,

    @Schema(description = "Отчество пользователя", example = "Michael", required = false)
    val middleName: String? = null,

    @Schema(description = "Роль пользователя в системе", example = "Администратор", required = false)
    val role: String? = null,

    @Schema(description = "Показывает, является ли пользователь студентом МИФИ", example = "true", required = false)
    val isStudentMifi: Boolean? = null,

    @Schema(description = "Идентификатор организации, к которой принадлежит пользователь", example = "5", required = false)
    val organizationId: Long? = null,

    @Schema(description = "Указывает, включена ли двухфакторная аутентификация", example = "true", required = false)
    val twoFactorAuthEnabled: Boolean? = null,

    @Schema(description = "Набор идентификаторов курсов, ассоциированных с пользователем", example = "[101, 102, 103]", required = false)
    val courses: Set<CourseDTO> = emptySet()
)

@Transactional
fun UserDTO.toEntity(courseRepository: CourseRepository): UserEntity {
    val userEntity = UserEntity.create(
        login = this.login,
        password = this.password,
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        middleName = this.middleName,
        role = this.role,
        isStudentMifi = this.isStudentMifi,
        organization = this.organizationId?.let { organizationId ->
            // Добавьте логику получения OrganizationEntity по organizationId
            // Например: organizationRepository.findById(organizationId).orElse(null)
            OrganizationEntity(organizationId = organizationId)
        },
        twoFactorAuthEnabled = this.twoFactorAuthEnabled
    )
    userEntity.userId = this.userId

    // Обновляем связи с курсами, используя Set для избежания дубликатов
    userEntity.userCourses = this.courses.mapNotNull { courseDto ->
        // Добавьте логику получения CourseEntity по courseId
        // Например: courseRepository.findById(courseDto.courseId).orElse(null)
        CourseEntity(courseId = courseDto.courseId).let { courseEntity ->
            UserCourseEntity.create( userEntity, courseEntity)
        }
    }.toMutableSet()

    return userEntity
}