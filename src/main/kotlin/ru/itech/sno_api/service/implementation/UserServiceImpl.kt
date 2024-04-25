package ru.itech.sno_api.service.implementation

import org.springframework.stereotype.Service
import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.password.PasswordEncoder
import ru.itech.sno_api.dto.UserDTO
import ru.itech.sno_api.entity.UserEntity
import ru.itech.sno_api.entity.toDTO
import ru.itech.sno_api.repository.UserRepository
import ru.itech.sno_api.repository.CourseRepository
import jakarta.persistence.EntityNotFoundException
import ru.itech.sno_api.dto.toEntity
import ru.itech.sno_api.entity.OrganizationEntity
import ru.itech.sno_api.entity.CourseEntity
import ru.itech.sno_api.service.UserService

@Service
class UserServiceImplementation(
    private val userRepository: UserRepository,
    private val courseRepository: CourseRepository,  // Inject the course repository
    private val passwordEncoder: PasswordEncoder
) : UserService {

    override fun getAll(): List<UserDTO> =
        userRepository.findAll().map(UserEntity::toDTO)

    override fun getAllP(pageIndex: Int, pageSize: Int): List<UserDTO> =
        userRepository.findByOrderByUserId(PageRequest.of(pageIndex, pageSize)).map(UserEntity::toDTO)

    override fun getById(userId: Long): UserDTO =
        userRepository.findById(userId)
            .orElseThrow { EntityNotFoundException("User with ID $userId not found") }
            .toDTO()

    override fun create(user: UserDTO): UserDTO {
        user.password = passwordEncoder.encode(user.password)
        val newUser = user.toEntity().also {
            it.courses = user.courses.mapNotNull { id ->
                courseRepository.findById(id).orElse(null)
            }.toMutableSet()
        }
        return userRepository.save(newUser).toDTO()
    }

    override fun update(userId: Long, user: UserDTO): UserDTO {
        val existingUser = userRepository.findById(userId)
            .orElseThrow { EntityNotFoundException("User with ID $userId not found") }

        existingUser.apply {
            email = user.email
            login = user.login
            password = passwordEncoder.encode(user.password)
            firstName = user.firstName
            lastName = user.lastName
            middleName = user.middleName
            role = user.role
            isStudentMifi = user.isStudentMifi
            twoFactorAuthEnabled = user.twoFactorAuthEnabled
            organization = user.organizationId?.let { OrganizationEntity().apply { organizationId = it } }

            // Update the courses
            courses.clear()  // Clear existing courses before adding new ones
            courses.addAll(user.courses.mapNotNull { courseId ->
                courseRepository.findById(courseId).orElse(null)
            }.filterNotNull().toMutableSet())
        }
        return userRepository.save(existingUser).toDTO()
    }

    override fun delete(userId: Long) =
        userRepository.deleteById(userId)
}
