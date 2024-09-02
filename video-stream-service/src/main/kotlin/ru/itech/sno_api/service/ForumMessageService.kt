package ru.itech.sno_api.service

import org.springframework.stereotype.Service
import ru.itech.sno_api.dto.ForumMessageDTO

@Service
interface ForumMessageService {

    fun getAll(): List<ForumMessageDTO>

    fun getById(messageId: Long): ForumMessageDTO

    fun create(message: ForumMessageDTO): ForumMessageDTO

    fun update(messageId: Long, message: ForumMessageDTO): ForumMessageDTO

    fun delete(messageId: Long)
}
