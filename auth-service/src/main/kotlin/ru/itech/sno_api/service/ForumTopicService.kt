package ru.itech.sno_api.service

import org.springframework.stereotype.Service
import ru.itech.sno_api.dto.ForumTopicDTO

@Service
interface ForumTopicService {

    fun getAll(): List<ForumTopicDTO>

    fun getById(topicId: Long): ForumTopicDTO

    fun create(topic: ForumTopicDTO): ForumTopicDTO

    fun update(topicId: Long, topic: ForumTopicDTO): ForumTopicDTO

    fun delete(topicId: Long)
}
