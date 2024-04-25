package ru.itech.sno_api.entity
import jakarta.persistence.*


@Entity
@Table(name = "forum_topic")
class ForumTopicEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    var topicId: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forum_id")
    var forum: ForumEntity = ForumEntity(),

    @Column(name = "title", nullable = false, length = 255)
    var title: String = "",

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    var description: String? = null
) {
    override fun toString(): String {
        return "ForumTopicEntity(topicId=$topicId, forum=$forum, title='$title', description=$description)"
    }
}
