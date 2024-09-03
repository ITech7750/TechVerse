package ru.itech.sno_api.entity

import jakarta.persistence.*
import ru.itech.sno_api.dto.OrganizationDTO

@Entity
@Table(name = "organization")
class OrganizationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id")
    var organizationId: Long? = null,
    @Column(name = "university")
    var university: String = "",
    @Column(name = "faculty")
    var faculty: String = "",
    @Column(name = "group_name")
    var groupName: String = "",
    @Column(name = "is_soft_deleted")
    var isSoftDeleted: Boolean = false
)
fun OrganizationEntity.toDTO(): OrganizationDTO {
    return OrganizationDTO(
        organizationId = this.organizationId ?: 0,  // Handle null case if ID is null, though typically it should not be.
        university = this.university,
        faculty = this.faculty,
        groupName = this.groupName,
        isSoftDeleted = this.isSoftDeleted
    )
}