package ru.itech.sno_api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import ru.itech.sno_api.dto.ForumTopicDTO
import ru.itech.sno_api.service.ForumTopicService

@RestController
@RequestMapping("/api/v1/forum-topics")
@Tag(
    name = "Forum Topic API",
    description = "Управление темами форума"
)
class ForumTopicController(
    private val forumTopicService: ForumTopicService
) {

    @Operation(method = "Получение всех тем форума")
    @GetMapping
    fun getAll(): List<ForumTopicDTO> =
        forumTopicService.getAll()

    @Operation(method = "Получение темы форума по идентификатору")
    @GetMapping("/{topicId}")
    fun getById(@PathVariable topicId: Long): ForumTopicDTO =
        forumTopicService.getById(topicId)

    @Operation(method = "Создание новой темы форума")
    @PostMapping
    fun create(@RequestBody topic: ForumTopicDTO): ForumTopicDTO =
        forumTopicService.create(topic)

    @Operation(method = "Обновление данных темы форума")
    @PutMapping("/{topicId}")
    fun update(@PathVariable topicId: Long, @RequestBody topic: ForumTopicDTO): ForumTopicDTO =
        forumTopicService.update(topicId, topic)

    @Operation(method = "Удаление темы форума")
    @DeleteMapping("/{topicId}")
    fun delete(@PathVariable topicId: Long) =
        forumTopicService.delete(topicId)
}
