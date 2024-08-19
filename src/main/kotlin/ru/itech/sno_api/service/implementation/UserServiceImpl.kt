package ru.itech.sno_api.service.implementation

import org.springframework.stereotype.Service
import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.password.PasswordEncoder
import ru.itech.sno_api.dto.UserDTO
import ru.itech.sno_api.repository.UserRepository
import ru.itech.sno_api.repository.CourseRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.transaction.annotation.Transactional
import ru.itech.sno_api.dto.toEntity
import ru.itech.sno_api.entity.*
import ru.itech.sno_api.repository.OrganizationRepository
import ru.itech.sno_api.service.UserService

@Service
@Transactional
open class UserServiceImplementation(
    private val userRepository: UserRepository,
    private val courseRepository: CourseRepository,
    private val passwordEncoder: PasswordEncoder,
    private val organizationRepository: OrganizationRepository,
) : UserService {

    override fun getAll(): List<UserDTO> =
        userRepository.findAll().map(UserEntity::toDTO)

    override fun getAllP(pageIndex: Int, pageSize: Int): List<UserDTO> =
        userRepository.findByOrderByUserId(PageRequest.of(pageIndex, pageSize)).map(UserEntity::toDTO)

    override fun getById(userId: Long): UserDTO =
        userRepository.findById(userId)
            .orElseThrow { EntityNotFoundException("User with ID $userId not found") }
            .toDTO()

    override fun create(userDTO: UserDTO): UserDTO {
        userDTO.password = passwordEncoder.encode(userDTO.password) // Encrypt the password before saving
        val newUser = userDTO.toEntity(courseRepository)
        return userRepository.save(newUser).toDTO()
    }

    override fun update(userId: Long, userDTO: UserDTO): UserDTO {
        val existingUser = userRepository.findById(userId).orElseThrow {
            EntityNotFoundException("User with ID $userId not found")
        }

        existingUser.userCourses = userDTO.courses.mapNotNull { courseDto ->
            courseRepository.findById(courseDto.courseId).orElse(null)?.let { courseEntity ->
                UserCourseEntity.create(existingUser, courseEntity) // Use actual entities
            }
        }.toMutableSet()

        existingUser.email = userDTO.email
        existingUser.login = userDTO.login
        existingUser.password = passwordEncoder.encode(userDTO.password) // Update and encrypt password
        existingUser.firstName = userDTO.firstName
        existingUser.lastName = userDTO.lastName
        existingUser.middleName = userDTO.middleName
        existingUser.role = userDTO.role
        existingUser.isStudentMifi = userDTO.isStudentMifi
        existingUser.twoFactorAuthEnabled = userDTO.twoFactorAuthEnabled
        existingUser.organization = userDTO.organizationId?.let { organizationId ->
            organizationRepository.findById(organizationId).orElse(null)
        }

        return userRepository.save(existingUser).toDTO()
    }

    override fun delete(userId: Long) =
        userRepository.deleteById(userId)

    // Частичное обновление параметров
    override fun updateFirstName(userId: Long, firstName: String): UserDTO {
        val user = userRepository.findById(userId).orElseThrow {
            EntityNotFoundException("User with ID $userId not found")
        }
        user.firstName = firstName
        return userRepository.save(user).toDTO()
    }

    override fun updateLastName(userId: Long, lastName: String): UserDTO {
        val user = userRepository.findById(userId).orElseThrow {
            EntityNotFoundException("User with ID $userId not found")
        }
        user.lastName = lastName
        return userRepository.save(user).toDTO()
    }

    override fun updateMiddleName(userId: Long, middleName: String): UserDTO {
        val user = userRepository.findById(userId).orElseThrow {
            EntityNotFoundException("User with ID $userId not found")
        }
        user.middleName = middleName
        return userRepository.save(user).toDTO()
    }

    override fun updateOrganization(userId: Long, organizationId: Long?): UserDTO {
        val user = userRepository.findById(userId).orElseThrow {
            EntityNotFoundException("User with ID $userId not found")
        }
        user.organization = organizationId?.let { id ->
            organizationRepository.findById(id).orElse(null)
        }
        return userRepository.save(user).toDTO()
    }

    override fun updateRole(userId: Long, role: String): UserDTO {
        val user = userRepository.findById(userId).orElseThrow {
            EntityNotFoundException("User with ID $userId not found")
        }
        user.role = role
        return userRepository.save(user).toDTO()
    }

    override fun updateIsStudentMifi(userId: Long, isStudentMifi: Boolean): UserDTO {
        val user = userRepository.findById(userId).orElseThrow {
            EntityNotFoundException("User with ID $userId not found")
        }
        user.isStudentMifi = isStudentMifi
        return userRepository.save(user).toDTO()
    }

    override fun updateTwoFactorAuthEnabled(userId: Long, twoFactorAuthEnabled: Boolean): UserDTO {
        val user = userRepository.findById(userId).orElseThrow {
            EntityNotFoundException("User with ID $userId not found")
        }
        user.twoFactorAuthEnabled = twoFactorAuthEnabled
        return userRepository.save(user).toDTO()
    }

    override fun updateEmail(userId: Long, email: String): UserDTO {
        val user = userRepository.findById(userId).orElseThrow {
            EntityNotFoundException("User with ID $userId not found")
        }
        user.email = email
        return userRepository.save(user).toDTO()
    }
}
