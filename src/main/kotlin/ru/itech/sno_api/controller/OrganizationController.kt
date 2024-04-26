package ru.itech.sno_api.controller



import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import ru.itech.sno_api.dto.OrganizationDTO
import ru.itech.sno_api.service.OrganizationService

@RestController
@RequestMapping("/api/v1/organizations")
@Tag(
    name = "Organization API",
    description = "Управление организациями"
)
class OrganizationController(
    private val organizationService: OrganizationService
) {

    @Operation(method = "Получение всех организаций")
    @GetMapping
    fun getAll(): List<OrganizationDTO> =
        organizationService.getAll()

    @Operation(method = "Получение организации по идентификатору")
    @GetMapping("/{organizationId}")
    fun getById(@PathVariable organizationId: Long): OrganizationDTO =
        organizationService.getById(organizationId)

    @Operation(method = "Создание новой организации")
    @PostMapping
    fun create(@RequestBody organization: OrganizationDTO): OrganizationDTO =
        organizationService.create(organization)

    @Operation(method = "Обновление данных организации")
    @PutMapping("/{organizationId}")
    fun update(@PathVariable organizationId: Long, @RequestBody organization: OrganizationDTO): OrganizationDTO =
        organizationService.update(organizationId, organization)

    @Operation(method = "Удаление организации")
    @DeleteMapping("/{organizationId}")
    fun delete(@PathVariable organizationId: Long) =
        organizationService.delete(organizationId)
}
