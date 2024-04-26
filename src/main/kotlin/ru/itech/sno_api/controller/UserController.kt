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
}
