package ru.itech.sno_api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import ru.itech.sno_api.dto.ForumParticipantRoleDTO
import ru.itech.sno_api.service.ForumParticipantRoleService

@RestController
@RequestMapping("/api/v1/forum-participant-roles")
@Tag(
    name = "Forum Participant Role API",
    description = "Роли участников форума"
)
class ForumParticipantRoleController(
    private val forumParticipantRoleService: ForumParticipantRoleService
) {

    @Operation(method = "Получение всех ролей участников форума")
    @GetMapping
    fun getAll(): List<ForumParticipantRoleDTO> =
        forumParticipantRoleService.getAll()

    @Operation(method = "Получение роли участника форума по идентификатору")
    @GetMapping("/{roleParticipantId}")
    fun getById(@PathVariable roleParticipantId: Long): ForumParticipantRoleDTO =
        forumParticipantRoleService.getById(roleParticipantId)

    @Operation(method = "Создание новой роли участника форума")
    @PostMapping
    fun create(@RequestBody role: ForumParticipantRoleDTO): ForumParticipantRoleDTO =
        forumParticipantRoleService.create(role)

    @Operation(method = "Обновление данных роли участника форума")
    @PutMapping("/{roleParticipantId}")
    fun update(@PathVariable roleParticipantId: Long, @RequestBody role: ForumParticipantRoleDTO): ForumParticipantRoleDTO =
        forumParticipantRoleService.update(roleParticipantId, role)

    @Operation(method = "Удаление роли участника форума")
    @DeleteMapping("/{roleParticipantId}")
    fun delete(@PathVariable roleParticipantId: Long) =
        forumParticipantRoleService.delete(roleParticipantId)
}
