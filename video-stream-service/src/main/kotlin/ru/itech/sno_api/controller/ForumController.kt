package ru.itech.sno_api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import ru.itech.sno_api.dto.ForumDTO
import ru.itech.sno_api.service.ForumService

@RestController
@RequestMapping("/api/v1/forums")
@Tag(
    name = "Forum API",
    description = "Управление форумами"
)
class ForumController(
    private val forumService: ForumService
) {

    @Operation(method = "Получение всех форумов")
    @GetMapping
    fun getAll(): List<ForumDTO> =
        forumService.getAll()

    @Operation(method = "Получение форума по идентификатору")
    @GetMapping("/{forumId}")
    fun getById(@PathVariable forumId: Long): ForumDTO =
        forumService.getById(forumId)

    @Operation(method = "Создание нового форума")
    @PostMapping
    fun create(@RequestBody forum: ForumDTO): ForumDTO =
        forumService.create(forum)

    @Operation(method = "Обновление данных форума")
    @PutMapping("/{forumId}")
    fun update(@PathVariable forumId: Long, @RequestBody forum: ForumDTO): ForumDTO =
        forumService.update(forumId, forum)

    @Operation(method = "Удаление форума")
    @DeleteMapping("/{forumId}")
    fun delete(@PathVariable forumId: Long) =
        forumService.delete(forumId)
}
