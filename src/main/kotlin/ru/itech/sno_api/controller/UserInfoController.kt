package ru.itech.sno_api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

import org.springframework.web.bind.annotation.*
import ru.itech.sno_api.core.JwtHelper


import ru.itech.sno_api.dto.UserInfoDTO
import ru.itech.sno_api.service.UserInfoService



data class Token(
    val access_token: String,
    val refresh_token: String,
)

@RestController
@RequestMapping("/api/users")
@Tag(
    name = "User API",
    description = "Пользователи"
)
class UserInfoController(
    private val userService: UserInfoService,
) {

    @Operation(method = "Получение всех пользователей")
    @GetMapping
    fun getAll(): List<UserInfoDTO> =
        userService.getAll()

    @GetMapping("/paginated")
    fun getAllP(@RequestParam("page") pageIndex: Int) : List<UserInfoDTO> =
        userService.getAllP(pageIndex)


    @Operation(method = "Получение пользователя по идентификатору")
    @GetMapping("/{userId}")
    fun getById(@PathVariable userId: Long): UserInfoDTO =
        userService.getById(userId)

    @Operation(method = "Создание нового пользователя")
    @PostMapping
    fun create(@RequestBody user: UserInfoDTO): UserInfoDTO =
        userService.create(user)

    @Operation(method = "Обновление данных пользователя")
    @PutMapping("/{userId}")
    fun update(@PathVariable userId: Long, @RequestBody user: UserInfoDTO): UserInfoDTO =
        userService.update(userId, user)

    @Operation(method = "Удаление пользователя")
    @DeleteMapping("/{userId}")
    fun delete(@PathVariable userId: Long) =
        userService.delete(userId)
}
