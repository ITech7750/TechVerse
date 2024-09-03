package ru.itech.sno_api.service

import org.springframework.stereotype.Component
import ru.itech.sno_api.dto.ForumDTO

@Component
interface ForumService {

    fun getAll(): List<ForumDTO>

    fun getById(forumId: Long): ForumDTO

    fun create(forum: ForumDTO): ForumDTO

    fun update(forumId: Long, forum: ForumDTO): ForumDTO

    fun delete(forumId: Long)
}
