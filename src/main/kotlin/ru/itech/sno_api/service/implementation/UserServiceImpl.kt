package ru.itech.sno_api.service.implementation

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import ru.itech.sno_api.dto.OrganizationDTO
import ru.itech.sno_api.dto.UserInfoDTO
import ru.itech.sno_api.entity.OrganizationEntity
import ru.itech.sno_api.entity.UserInfoEntity
import ru.itech.sno_api.repository.UserInfoRepository
import ru.itech.sno_api.service.UserInfoService
import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.password.PasswordEncoder
import ru.itech.sno_api.dto.UserDTO
import ru.itech.sno_api.entity.UserEntity
import ru.itech.sno_api.repository.OrganizationRepository
import ru.itech.sno_api.repository.UserRepository
import ru.itech.sno_api.service.UserService

@Service
class UserServiceImplementation(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
): UserService {



    /**
     * Получить всех пользователей
     */
    override fun getAll(): List<UserDTO> {
        return userRepository.findAll()
            .map { it.toDTO() }
    }


    /**
     * Получить всех пользователей по странице
     */
    override fun getAllP(pageIndex: Int): List<UserDTO> {
        return userRepository.findByOrderByUserId(PageRequest.of(pageIndex,2))
            .map { it.toDTO() }
    }


    /**
     * Получить пользователя по идентификатору
     */
    override fun getById(userId: Long): UserDTO {
        return userRepository.findById(userId)
            .orElseThrow { throw EntityNotFoundException("User with ID $userId not found") }
            .toDTO()
    }

    /**
     * Создать нового пользователя
     */
    override fun create(user: UserDTO): UserDTO {
        return userRepository.save(user.toEntity(passwordEncoder))
            .toDTO()
    }

    /**
     * Обновить данные пользователя
     */
    override fun update(userId: Long, user: UserDTO): UserDTO {
        val existingUser = userRepository.findById(userId)
            .orElseThrow { EntityNotFoundException("User with ID $userId not found") }

        existingUser.apply {
            email = user.email
            login = user.login
            password = user.password


        }

        return userRepository.save(existingUser).toDTO()
    }


    override fun delete(userId: Long) {
        userRepository.deleteById(userId)
    }
}

fun UserEntity.toDTO(): UserDTO {
    return UserDTO(
        login = this.login,
        password = this.password,
        email = this.email,
    )
}

fun UserDTO.toEntity(passwordEncoder: PasswordEncoder): UserEntity {
    val hashedPassword = passwordEncoder.encode(password)
    return UserEntity(
        login = login,
        email = email,
        password = hashedPassword
    )
}