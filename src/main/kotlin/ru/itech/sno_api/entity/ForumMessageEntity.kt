package ru.itech.sno_api.entity
import jakarta.persistence.*
import ru.itech.sno_api.dto.ForumMessageDTO
import java.sql.Timestamp


@Entity
@Table(name = "forum_message")
class ForumMessageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    var messageId: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    var topic: ForumTopicEntity? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserEntity? = null,

    @Column(name = "reply_id")
    var replyId: Long = 0,

    @Column(name = "message_text")
    var messageText: String = "",

    @Column(name = "timestamp")
    var timestamp: Timestamp = Timestamp(System.currentTimeMillis())
)
fun ForumMessageEntity.toDTO(): ForumMessageDTO {
    return ForumMessageDTO(
        messageId = this.messageId,
        topicId = this.topic?.topicId ?: 0,
        userId = this.user?.userId ?: 0,
        replyId = this.replyId,
        messageText = this.messageText,
        timestamp = this.timestamp
    )
}