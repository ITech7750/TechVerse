package ru.itech.sno_api.service.implementation
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import ru.itech.sno_api.dto.ForumMessageDTO
import ru.itech.sno_api.dto.toDTO
import ru.itech.sno_api.entity.ForumMessageEntity
import ru.itech.sno_api.entity.toEntity
import ru.itech.sno_api.repository.ForumMessageRepository
import ru.itech.sno_api.service.ForumMessageService

@Service
class ForumMessageServiceImplementation(
    private val forumMessageRepository: ForumMessageRepository
): ForumMessageService {

    override fun getAll(): List<ForumMessageDTO> {
        return forumMessageRepository.findAll()
            .map { it.toDTO() }
    }

    override fun getById(messageId: Long): ForumMessageDTO {
        return forumMessageRepository.findById(messageId)
            .orElseThrow { throw EntityNotFoundException("Forum message with ID $messageId not found") }
            .toDTO()
    }

    override fun create(message: ForumMessageDTO): ForumMessageDTO {
        return forumMessageRepository.save(message.toEntity())
            .toDTO()
    }

    override fun update(messageId: Long, message: ForumMessageDTO): ForumMessageDTO {
        val existingMessage = forumMessageRepository.findById(messageId)
            .orElseThrow { throw EntityNotFoundException("Forum message with ID $messageId not found") }

        existingMessage.messageText = message.messageText
        existingMessage.timestamp = message.timestamp
        existingMessage.replyId = message.replyId
        existingMessage.topic = message.topic.toEntity()
        existingMessage.user = message.user.toEntity()

        return forumMessageRepository.save(existingMessage)
            .toDTO()
    }

    override fun delete(messageId: Long) {
        forumMessageRepository.deleteById(messageId)
    }
}

fun ForumMessageEntity.toDTO(): ForumMessageDTO {
    return ForumMessageDTO(
        messageId = messageId,
        messageText = messageText,
        timestamp = timestamp,
        replyId = replyId,
        topic = topic!!.toDTO(),
        user = user!!.toDTO()
    )
}

fun ForumMessageDTO.toEntity(): ForumMessageEntity {
    return ForumMessageEntity(
        messageId = messageId,
        messageText = messageText,
        timestamp = timestamp,
        replyId = replyId,
        topic = topic.toEntity(),
        user = user.toEntity()
    )
}


