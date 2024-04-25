package ru.itech.sno_api.service.implementation

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import ru.itech.sno_api.dto.ForumParticipantDTO
import ru.itech.sno_api.dto.toDTO
import ru.itech.sno_api.dto.toEntity
import ru.itech.sno_api.entity.ForumParticipantEntity
import ru.itech.sno_api.entity.toDTO
import ru.itech.sno_api.entity.toEntity
import ru.itech.sno_api.repository.ForumParticipantRepository
import ru.itech.sno_api.service.ForumParticipantService

@Service
class ForumParticipantServiceImplementation(
    private val forumParticipantRepository: ForumParticipantRepository
): ForumParticipantService {

    override fun getAll(): List<ForumParticipantDTO> {
        return forumParticipantRepository.findAll()
            .map { it.toDTO() }
    }

    override fun getById(participantId: Long): ForumParticipantDTO {
        return forumParticipantRepository.findById(participantId)
            .orElseThrow { throw EntityNotFoundException("Forum participant with ID $participantId not found") }
            .toDTO()
    }

    override fun create(participant: ForumParticipantDTO): ForumParticipantDTO {
        return forumParticipantRepository.save(participant.toEntity())
            .toDTO()
    }

    override fun update(participantId: Long, participant: ForumParticipantDTO): ForumParticipantDTO {
        val existingParticipant = forumParticipantRepository.findById(participantId)
            .orElseThrow { throw EntityNotFoundException("Forum participant with ID $participantId not found") }

        existingParticipant.forum = participant.forum.toEntity()
        existingParticipant.user = participant.user.toEntity()

        return forumParticipantRepository.save(existingParticipant)
            .toDTO()
    }

    override fun delete(participantId: Long) {
        forumParticipantRepository.deleteById(participantId)
    }
}
fun ForumParticipantEntity.toDTO(): ForumParticipantDTO {
    return ForumParticipantDTO(
        participantId = participantId,
        forum = forum.toDTO(),
        user = user.toDTO()
    )
}

fun ForumParticipantDTO.toEntity(): ForumParticipantEntity {
    return ForumParticipantEntity(
        participantId = participantId,
        forum = forum.toEntity(),
        user = user.toEntity()
    )
}

