package ru.itech.sno_api.service.implementation

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import ru.itech.sno_api.dto.ForumTopicDTO
import ru.itech.sno_api.dto.toEntity
import ru.itech.sno_api.entity.ForumTopicEntity
import ru.itech.sno_api.entity.toDTO
import ru.itech.sno_api.repository.ForumTopicRepository
import ru.itech.sno_api.service.ForumTopicService

@Service
class ForumTopicServiceImplementation(
    private val forumTopicRepository: ForumTopicRepository
): ForumTopicService {

    override fun getAll(): List<ForumTopicDTO> {
        return forumTopicRepository.findAll()
            .map { it.toDTO() }
    }

    override fun getById(topicId: Long): ForumTopicDTO {
        return forumTopicRepository.findById(topicId)
            .orElseThrow { throw EntityNotFoundException("Forum topic with ID $topicId not found") }
            .toDTO()
    }

    override fun create(topic: ForumTopicDTO): ForumTopicDTO {
        return forumTopicRepository.save(topic.toEntity())
            .toDTO()
    }

    override fun update(topicId: Long, topic: ForumTopicDTO): ForumTopicDTO {
        val existingTopic = forumTopicRepository.findById(topicId)
            .orElseThrow { throw EntityNotFoundException("Forum topic with ID $topicId not found") }

        existingTopic.title = topic.title
        existingTopic.description = topic.description

        return forumTopicRepository.save(existingTopic)
            .toDTO()
    }

    override fun delete(topicId: Long) {
        forumTopicRepository.deleteById(topicId)
    }
}
