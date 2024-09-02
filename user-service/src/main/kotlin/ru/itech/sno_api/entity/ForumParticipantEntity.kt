package ru.itech.sno_api.entity
import jakarta.persistence.*
import ru.itech.sno_api.dto.ForumParticipantDTO


@Entity
@Table(name = "forum_participant")
class ForumParticipantEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_id")
    var participantId: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forum_id")
    var forum: ForumEntity? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserEntity? = null
)
fun ForumParticipantEntity.toDTO(): ForumParticipantDTO {
    return ForumParticipantDTO(
        participantId = this.participantId,
        forumId = this.forum?.forumId ?: 0,
        userId = this.user?.userId ?: 0
    )
}