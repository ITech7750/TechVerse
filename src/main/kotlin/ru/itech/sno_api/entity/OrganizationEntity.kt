package ru.itech.sno_api.entity

import jakarta.persistence.*

@Entity
@Table(name = "organization")
class OrganizationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id")
    val organizationId: Long? = null,
    @Column(name = "university")
    var university: String = "",
    @Column(name = "faculty")
    var faculty: String = "",
    @Column(name = "group_name")
    var groupName: String = "",
    @Column(name = "is_soft_deleted")
    var isSoftDeleted: Boolean = false
)
