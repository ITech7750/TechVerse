package ru.itech.sno_api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import ru.itech.sno_api.dto.ForumParticipantDTO
import ru.itech.sno_api.service.ForumParticipantService

@RestController
@RequestMapping("/api/v1/forum-participants")
@Tag(
    name = "Forum participant API",
    description = "Участники форума"
)
class ForumParticipantController(
    private val forumParticipantService: ForumParticipantService
) {

    @Operation(method = "Получение всех участников форума")
    @GetMapping
    fun getAll(): List<ForumParticipantDTO> =
        forumParticipantService.getAll()

    @Operation(method = "Получение участника форума по идентификатору")
    @GetMapping("/{participantId}")
    fun getById(@PathVariable participantId: Long): ForumParticipantDTO =
        forumParticipantService.getById(participantId)

    @Operation(method = "Создание нового участника форума")
    @PostMapping
    fun create(@RequestBody participant: ForumParticipantDTO): ForumParticipantDTO =
        forumParticipantService.create(participant)

    @Operation(method = "Обновление данных участника форума")
    @PutMapping("/{participantId}")
    fun update(@PathVariable participantId: Long, @RequestBody participant: ForumParticipantDTO): ForumParticipantDTO =
        forumParticipantService.update(participantId, participant)

    @Operation(method = "Удаление участника форума")
    @DeleteMapping("/{participantId}")
    fun delete(@PathVariable participantId: Long) =
        forumParticipantService.delete(participantId)
}
