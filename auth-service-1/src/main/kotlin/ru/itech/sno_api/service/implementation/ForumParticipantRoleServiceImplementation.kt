package ru.itech.sno_api.service.implementation

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import ru.itech.sno_api.dto.ForumParticipantRoleDTO
import ru.itech.sno_api.dto.toEntity
import ru.itech.sno_api.entity.ForumParticipantRoleEntity
import ru.itech.sno_api.entity.toDTO
import ru.itech.sno_api.repository.ForumParticipantRoleRepository
import ru.itech.sno_api.service.ForumParticipantRoleService

@Service
class ForumParticipantRoleServiceImplementation(
    private val forumParticipantRoleRepository: ForumParticipantRoleRepository
): ForumParticipantRoleService {

    override fun getAll(): List<ForumParticipantRoleDTO> {
        return forumParticipantRoleRepository.findAll()
            .map { it.toDTO() }
    }

    override fun getById(roleId: Long): ForumParticipantRoleDTO {
        return forumParticipantRoleRepository.findById(roleId)
            .orElseThrow { throw EntityNotFoundException("Forum participant role with ID $roleId not found") }
            .toDTO()
    }

    override fun create(role: ForumParticipantRoleDTO): ForumParticipantRoleDTO {
        return forumParticipantRoleRepository.save(role.toEntity())
            .toDTO()
    }

    override fun update(roleId: Long, role: ForumParticipantRoleDTO): ForumParticipantRoleDTO {
        val existingRole = forumParticipantRoleRepository.findById(roleId)
            .orElseThrow { throw EntityNotFoundException("Forum participant role with ID $roleId not found") }

        existingRole.participant = role.participant.toEntity()
        existingRole.roleName = role.roleName

        return forumParticipantRoleRepository.save(existingRole)
            .toDTO()
    }

    override fun delete(roleId: Long) {
        forumParticipantRoleRepository.deleteById(roleId)
    }
}
