package ru.itech.sno_api.service

import org.springframework.stereotype.Service
import ru.itech.sno_api.dto.UserDTO
import ru.itech.sno_api.dto.UserInfoDTO

@Service
interface UserService {

    fun getAll(): List<UserDTO>

    fun getById(userId: Long): UserDTO

    fun create(user: UserDTO): UserDTO

    fun update(userId: Long, user: UserDTO): UserDTO

    fun delete(userId: Long)

    fun getAllP(pageIndex: Int) : List<UserDTO>

}