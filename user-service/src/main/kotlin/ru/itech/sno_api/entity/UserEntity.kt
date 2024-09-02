package ru.itech.sno_api.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import ru.itech.sno_api.dto.CourseDTO
import ru.itech.sno_api.dto.UserDTO
@Entity
@Table(name = "user_table")
class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var userId: Long = 0,

    @Column(unique = true, nullable = false)
    var login: String = "",

    @Column(nullable = false)
    var password: String = "",

    @Column(nullable = false)
    var email: String = "",

    @Column(name = "first_name")
    var firstName: String? = null,

    @Column(name = "last_name")
    var lastName: String? = null,

    @Column(name = "middle_name")
    var middleName: String? = null,

    @Column(name = "role")
    var role: String? = null,

    @Column(name = "is_student_mifi")
    var isStudentMifi: Boolean? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    var organization: OrganizationEntity? = null,

    @Column(name = "two_factor_auth_enabled")
    var twoFactorAuthEnabled: Boolean? = null,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonIgnore
    var userCourses: MutableSet<UserCourseEntity> = mutableSetOf()
) {
    companion object {
        fun create(
            login: String,
            password: String,
            email: String,
            firstName: String?,
            lastName: String?,
            middleName: String?,
            role: String?,
            isStudentMifi: Boolean?,
            organization: OrganizationEntity?,
            twoFactorAuthEnabled: Boolean?
        ): UserEntity {
            val user = UserEntity()
            user.login = login
            user.password = password
            user.email = email
            user.firstName = firstName
            user.lastName = lastName
            user.middleName = middleName
            user.role = role
            user.isStudentMifi = isStudentMifi
            user.organization = organization
            user.twoFactorAuthEnabled = twoFactorAuthEnabled
            return user
        }
    }
}

fun UserEntity.toDTO(): UserDTO {
    return UserDTO(
        login = this.login,
        password = this.password,
        email = this.email,
        userId = this.userId,
        firstName = this.firstName,
        lastName = this.lastName,
        middleName = this.middleName,
        role = this.role,
        isStudentMifi = this.isStudentMifi,
        organizationId = this.organization?.organizationId,
        twoFactorAuthEnabled = this.twoFactorAuthEnabled,
        courses = this.userCourses.mapNotNull { it.course?.toDTO() }.toSet()
    )
}
