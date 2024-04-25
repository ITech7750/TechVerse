package ru.itech.sno_api.service

import org.springframework.stereotype.Service
import ru.itech.sno_api.dto.UserDTO

@Service
interface UserService {

    fun getAll(): List<UserDTO>

    fun getById(userId: Long): UserDTO

    fun create(user: UserDTO): UserDTO

    fun update(userId: Long, user: UserDTO): UserDTO

    fun delete(userId: Long)

    fun getAllP(pageIndex: Int, pageSize: Int = 10): List<UserDTO>
}
