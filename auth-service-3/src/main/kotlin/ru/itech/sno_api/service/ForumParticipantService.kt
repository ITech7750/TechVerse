package ru.itech.sno_api.service

import org.springframework.stereotype.Service
import ru.itech.sno_api.dto.ForumParticipantDTO

@Service
interface ForumParticipantService {

    fun getAll(): List<ForumParticipantDTO>

    fun getById(participantId: Long): ForumParticipantDTO

    fun create(participant: ForumParticipantDTO): ForumParticipantDTO

    fun update(participantId: Long, participant: ForumParticipantDTO): ForumParticipantDTO

    fun delete(participantId: Long)
}
