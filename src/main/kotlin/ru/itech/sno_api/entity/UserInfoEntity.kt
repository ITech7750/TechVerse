package ru.itech.sno_api.entity

import jakarta.persistence.*

@Entity
@Table(name = "user_info")
data class UserInfoEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val userId: Long? = null,

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
    var twoFactorAuthEnabled: Boolean = false
)
