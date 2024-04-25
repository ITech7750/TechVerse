package ru.itech.sno_api.controller

import org.springframework.security.access.prepost.PreAuthorize
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import ru.itech.sno_api.dto.AuthorizationDTO
import ru.itech.sno_api.service.AuthorizationService

@RestController
@RequestMapping("/api/v1/authorizations")
@Tag(
    name = " Authorization API",
    description = "Авторизации"
)
class AuthorizationController(
    private val authorizationService: AuthorizationService
) {

    @Operation(method = "Получение всех авторизаций")
    @GetMapping
    fun getAll(): List<AuthorizationDTO> =
        authorizationService.getAll()

    @PreAuthorize("isAuthenticated()")
    @Operation(method = "Получение авторизации по идентификатору")
    @GetMapping("/{authId}")
    fun getById(@PathVariable authId: Long): AuthorizationDTO =
        authorizationService.getById(authId)

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(method = "Создание новой авторизации")
    @PostMapping
    fun create(@RequestBody authorization: AuthorizationDTO): AuthorizationDTO =
        authorizationService.create(authorization)

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(method = "Обновление данных авторизации")
    @PutMapping("/{authId}")
    fun update(@PathVariable authId: Long, @RequestBody authorization: AuthorizationDTO): AuthorizationDTO =
        authorizationService.update(authId, authorization)

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(method = "Удаление авторизации")
    @DeleteMapping("/{authId}")
    fun delete(@PathVariable authId: Long) =
        authorizationService.delete(authId)
}
