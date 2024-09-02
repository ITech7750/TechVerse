package ru.itech.sno_api.service

import org.springframework.stereotype.Service
import ru.itech.sno_api.dto.ForumParticipantRoleDTO

@Service
interface ForumParticipantRoleService {

    fun getAll(): List<ForumParticipantRoleDTO>

    fun getById(roleParticipantId: Long): ForumParticipantRoleDTO

    fun create(role: ForumParticipantRoleDTO): ForumParticipantRoleDTO

    fun update(roleParticipantId: Long, role: ForumParticipantRoleDTO): ForumParticipantRoleDTO

    fun delete(roleParticipantId: Long)
}
