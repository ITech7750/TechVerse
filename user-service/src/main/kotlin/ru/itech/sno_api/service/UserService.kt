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

    // Частичное обновление параметров
    fun updateFirstName(userId: Long, firstName: String): UserDTO

    fun updateLastName(userId: Long, lastName: String): UserDTO

    fun updateMiddleName(userId: Long, middleName: String): UserDTO

    fun updateOrganization(userId: Long, organizationId: Long?): UserDTO

    fun updateRole(userId: Long, role: String): UserDTO

    fun updateIsStudentMifi(userId: Long, isStudentMifi: Boolean): UserDTO

    fun updateTwoFactorAuthEnabled(userId: Long, twoFactorAuthEnabled: Boolean): UserDTO

    fun updateEmail(userId: Long, email: String): UserDTO
}
