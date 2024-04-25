package ru.itech.sno_api.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.itech.sno_api.dto.UserDTO
import ru.itech.sno_api.service.UserService

@RestController
@RequestMapping("/api/users/auth")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserDTO>> {
        val users = userService.getAll()
        return ResponseEntity.ok(users)
    }

    @GetMapping("/page/{pageIndex}")
    fun getUsersByPage(@PathVariable pageIndex: Int): ResponseEntity<List<UserDTO>> {
        val users = userService.getAllP(pageIndex)
        return ResponseEntity.ok(users)
    }

    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: Long): ResponseEntity<UserDTO> {
        val user = userService.getById(userId)
        return ResponseEntity.ok(user)
    }

    @PostMapping
    fun createUser(@RequestBody user: UserDTO): ResponseEntity<UserDTO> {
        val savedUser = userService.create(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser)
    }

    @PutMapping("/{userId}")
    fun updateUser(@PathVariable userId: Long, @RequestBody user: UserDTO): ResponseEntity<UserDTO> {
        val updatedUser = userService.update(userId, user)
        return ResponseEntity.ok(updatedUser)
    }

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: Long): ResponseEntity<Void> {
        userService.delete(userId)
        return ResponseEntity.noContent().build()
    }
}
