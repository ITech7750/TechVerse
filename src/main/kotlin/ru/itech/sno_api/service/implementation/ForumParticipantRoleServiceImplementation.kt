package ru.itech.sno_api.service.implementation

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import ru.itech.sno_api.dto.ForumParticipantRoleDTO
import ru.itech.sno_api.entity.ForumParticipantRoleEntity
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

    override fun getById(roleParticipantId: Long): ForumParticipantRoleDTO {
        return forumParticipantRoleRepository.findById(roleParticipantId)
            .orElseThrow { throw EntityNotFoundException("Forum participant role with ID $roleParticipantId not found") }
            .toDTO()
    }

    override fun create(role: ForumParticipantRoleDTO): ForumParticipantRoleDTO {
        return forumParticipantRoleRepository.save(role.toEntity())
            .toDTO()
    }

    override fun update(roleParticipantId: Long, role: ForumParticipantRoleDTO): ForumParticipantRoleDTO {
        val existingRole = forumParticipantRoleRepository.findById(roleParticipantId)
            .orElseThrow { throw EntityNotFoundException("Forum participant role with ID $roleParticipantId not found") }

        existingRole.participant = role.participant.toEntity()
        existingRole.roleName = role.roleName
        existingRole.roleId = role.roleId

        return forumParticipantRoleRepository.save(existingRole)
            .toDTO()
    }

    override fun delete(roleParticipantId: Long) {
        forumParticipantRoleRepository.deleteById(roleParticipantId)
    }
}


fun ForumParticipantRoleEntity.toDTO(): ForumParticipantRoleDTO {
    return ForumParticipantRoleDTO(
        roleId = roleId,
        participant = participant.toDTO(),
        roleName = roleName
    )
}

fun ForumParticipantRoleDTO.toEntity(): ForumParticipantRoleEntity {
    return ForumParticipantRoleEntity(
        roleId = roleId,
        participant = participant.toEntity(),
        roleName = roleName
    )
}

