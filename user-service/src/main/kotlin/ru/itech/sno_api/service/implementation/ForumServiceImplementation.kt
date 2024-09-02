package ru.itech.sno_api.service.implementation

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import ru.itech.sno_api.dto.ForumDTO
import ru.itech.sno_api.dto.toEntity
import ru.itech.sno_api.entity.ForumEntity
import ru.itech.sno_api.entity.toDTO
import ru.itech.sno_api.repository.ForumRepository
import ru.itech.sno_api.service.ForumService


@Service
class ForumServiceImplementation(
    private val forumRepository: ForumRepository
): ForumService {

    override fun getAll(): List<ForumDTO> {
        return forumRepository.findAll()
            .map { it.toDTO() }
    }

    override fun getById(forumId: Long): ForumDTO {
        return forumRepository.findById(forumId)
            .orElseThrow { throw EntityNotFoundException("Forum with ID $forumId not found") }
            .toDTO()
    }

    override fun create(forum: ForumDTO): ForumDTO {
        return forumRepository.save(forum.toEntity())
            .toDTO()
    }

    override fun update(forumId: Long, forum: ForumDTO): ForumDTO {
        val existingForum = forumRepository.findById(forumId)
            .orElseThrow { throw EntityNotFoundException("Forum with ID $forumId not found") }

        existingForum.title = forum.title
        existingForum.description = forum.description

        return forumRepository.save(existingForum)
            .toDTO()
    }

    override fun delete(forumId: Long) {
        forumRepository.deleteById(forumId)
    }
}


