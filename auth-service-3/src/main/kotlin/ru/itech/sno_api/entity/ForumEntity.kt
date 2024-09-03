package ru.itech.sno_api.entity
import jakarta.persistence.*
import ru.itech.sno_api.dto.ForumDTO

@Entity
@Table(name = "forum")
class ForumEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forum_id")
    var forumId: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    var lecture: LectureEntity? = null,

    @Column(name = "title", nullable = false, length = 255)
    var title: String = "",

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    var description: String = ""
) {
    override fun toString(): String {
        return "ForumEntity(forumId=$forumId, title='$title', description=$description)"
    }
}

fun ForumEntity.toDTO(): ForumDTO {
    return ForumDTO(
        forumId = forumId,
        title = title,
        description = description,
        lectureId = lecture?.lectureId
    )
}


