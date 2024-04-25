package ru.itech.sno_api.service.implementation

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import ru.itech.sno_api.dto.ForumMessageDTO
import ru.itech.sno_api.dto.toEntity
import ru.itech.sno_api.entity.ForumMessageEntity
import ru.itech.sno_api.entity.ForumTopicEntity
import ru.itech.sno_api.entity.UserInfoEntity
import ru.itech.sno_api.entity.toDTO

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
        existingMessage.topic = ForumTopicEntity().apply { this.topicId = message.topicId }
        existingMessage.user = UserInfoEntity().apply { this.userId = message.userId }

        return forumMessageRepository.save(existingMessage)
            .toDTO()
    }

    override fun delete(messageId: Long) {
        forumMessageRepository.deleteById(messageId)
    }
}
