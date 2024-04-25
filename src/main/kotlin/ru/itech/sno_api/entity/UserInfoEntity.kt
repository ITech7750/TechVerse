package ru.itech.sno_api.entity

import jakarta.persistence.*
import ru.itech.sno_api.dto.UserInfoDTO

@Entity
@Table(name = "user_info")
data class UserInfoEntity(
    @Id
    @Column(name = "user_id")
    var userId: Long = 0,
    @Column(name = "first_name")
    var firstName: String = "",
    @Column(name = "last_name")
    var lastName: String = "",
    @Column(name = "middle_name")
    var middleName: String? = null,
    @Column(name = "role")
    var role: String = "",
    @Column(name = "is_student_mifi")
    var isStudentMifi: Boolean = false,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    var organization: OrganizationEntity? = null,
    @Column(name = "two_factor_auth_enabled")
    var twoFactorAuthEnabled: Boolean = false,
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserEntity = UserEntity(),

    @ManyToMany
    @JoinTable(
        name = "user_course",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "course_id")]
    )
    val courses: MutableSet<CourseEntity> = mutableSetOf()

)
fun UserInfoEntity.toDTO(): UserInfoDTO {
    return UserInfoDTO(
        userId = userId,
        firstName = this.firstName,
        lastName = this.lastName,
        middleName = this.middleName,
        role = this.role,
        isStudentMifi = this.isStudentMifi,
        organizationId = this.organization?.organizationId,
        twoFactorAuthEnabled = this.twoFactorAuthEnabled
    )
}