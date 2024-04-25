package ru.itech.sno_api.entity
import jakarta.persistence.*
import ru.itech.sno_api.dto.ForumParticipantRoleDTO


@Entity
@Table(name = "forum_participant_role")
class ForumParticipantRoleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    var roleId: Long = 0,
    @OneToOne
    @JoinColumn(name = "participant_id")
    var participant: ForumParticipantEntity = ForumParticipantEntity(),
    @Column(name = "role_name")
    var roleName: String = ""
)
fun ForumParticipantRoleEntity.toDTO(): ForumParticipantRoleDTO {
    return ForumParticipantRoleDTO(
        roleId = this.roleId,
        participant = this.participant.toDTO(), // Убедитесь, что у participant Entity есть метод toDTO()
        roleName = this.roleName
    )
}