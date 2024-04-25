package ru.itech.sno_api.service

import org.springframework.stereotype.Service
import ru.itech.sno_api.dto.UserInfoDTO


@Service
interface UserInfoService {

    fun getAll(): List<UserInfoDTO>

    fun getById(userId: Long): UserInfoDTO

    fun create(user: UserInfoDTO): UserInfoDTO

    fun update(userId: Long, user: UserInfoDTO): UserInfoDTO

    fun delete(userId: Long)

    fun getAllP(pageIndex: Int) : List<UserInfoDTO>

}
