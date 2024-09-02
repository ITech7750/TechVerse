package ru.itech.sno_api.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.itech.sno_api.dto.UserDTO
import ru.itech.sno_api.service.UserService

@RestController
@RequestMapping("/api/users")
@Tag(
    name = "User Management API",
    description = "Управление пользователями"
)
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserDTO>> = ResponseEntity.ok(userService.getAll())

    @GetMapping("/page/{pageIndex}")
    fun getUsersByPage(@PathVariable pageIndex: Int, @RequestParam(required = false, defaultValue = "10") pageSize: Int): ResponseEntity<List<UserDTO>> =
        ResponseEntity.ok(userService.getAllP(pageIndex, pageSize))

    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: Long): ResponseEntity<UserDTO> =
        ResponseEntity.ok(userService.getById(userId))

    @PostMapping
    fun createUser(@RequestBody user: UserDTO): ResponseEntity<UserDTO> =
        ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user))

    @PutMapping("/{userId}")
    fun updateUser(@PathVariable userId: Long, @RequestBody user: UserDTO): ResponseEntity<UserDTO> =
        ResponseEntity.ok(userService.update(userId, user))

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: Long): ResponseEntity<Void> {
        userService.delete(userId)
        return ResponseEntity.noContent().build()
    }

    // Частичное обновление параметров пользователя
    @PatchMapping("/{userId}/firstName")
    fun updateFirstName(@PathVariable userId: Long, @RequestBody firstName: String): ResponseEntity<UserDTO> =
        ResponseEntity.ok(userService.updateFirstName(userId, firstName))

    @PatchMapping("/{userId}/lastName")
    fun updateLastName(@PathVariable userId: Long, @RequestBody lastName: String): ResponseEntity<UserDTO> =
        ResponseEntity.ok(userService.updateLastName(userId, lastName))

    @PatchMapping("/{userId}/middleName")
    fun updateMiddleName(@PathVariable userId: Long, @RequestBody middleName: String): ResponseEntity<UserDTO> =
        ResponseEntity.ok(userService.updateMiddleName(userId, middleName))

    @PatchMapping("/{userId}/organization")
    fun updateOrganization(@PathVariable userId: Long, @RequestBody organizationId: Long?): ResponseEntity<UserDTO> =
        ResponseEntity.ok(userService.updateOrganization(userId, organizationId))

    @PatchMapping("/{userId}/role")
    fun updateRole(@PathVariable userId: Long, @RequestBody role: String): ResponseEntity<UserDTO> =
        ResponseEntity.ok(userService.updateRole(userId, role))

    @PatchMapping("/{userId}/isStudentMifi")
    fun updateIsStudentMifi(@PathVariable userId: Long, @RequestBody isStudentMifi: Boolean): ResponseEntity<UserDTO> =
        ResponseEntity.ok(userService.updateIsStudentMifi(userId, isStudentMifi))

    @PatchMapping("/{userId}/twoFactorAuthEnabled")
    fun updateTwoFactorAuthEnabled(@PathVariable userId: Long, @RequestBody twoFactorAuthEnabled: Boolean): ResponseEntity<UserDTO> =
        ResponseEntity.ok(userService.updateTwoFactorAuthEnabled(userId, twoFactorAuthEnabled))

    @PatchMapping("/{userId}/email")
    fun updateEmail(@PathVariable userId: Long, @RequestBody email: String): ResponseEntity<UserDTO> =
        ResponseEntity.ok(userService.updateEmail(userId, email))
}
