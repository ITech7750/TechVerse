package ru.itech.sno_api.service.implementation

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.itech.sno_api.dto.UserInfoDTO
import ru.itech.sno_api.entity.UserInfoEntity
import ru.itech.sno_api.repository.UserInfoRepository
import ru.itech.sno_api.repository.OrganizationRepository
import ru.itech.sno_api.service.UserInfoService
import org.springframework.data.domain.PageRequest
import ru.itech.sno_api.dto.toEntity
import ru.itech.sno_api.entity.toDTO

@Service
@Transactional
open class UserInfoServiceImplementation(
    private val userRepository: UserInfoRepository,
    private val organizationRepository: OrganizationRepository
): UserInfoService {

    override fun getAll(): List<UserInfoDTO> {
        return userRepository.findAll()
            .map { it.toDTO() }
    }

    override fun getAllP(pageIndex: Int): List<UserInfoDTO> {
        return userRepository.findByOrderByUserId(PageRequest.of(pageIndex, 2))
            .map { it.toDTO() }
    }

    override fun getById(userId: Long): UserInfoDTO {
        return userRepository.findById(userId)
            .orElseThrow { EntityNotFoundException("User with ID $userId not found") }
            .toDTO()
    }

    override fun create(user: UserInfoDTO): UserInfoDTO {
        return userRepository.save(user.toEntity())
            .toDTO()
    }

    override fun update(userId: Long, user: UserInfoDTO): UserInfoDTO {
        val existingUser = userRepository.findById(userId)
            .orElseThrow { EntityNotFoundException("User with ID $userId not found") }

        existingUser.apply {
            firstName = user.firstName
            lastName = user.lastName
            middleName = user.middleName
            role = user.role
            isStudentMifi = user.isStudentMifi
            organization = user.organizationId?.let {
                organizationRepository.findById(it).orElseThrow {
                    EntityNotFoundException("Organization with ID $it not found")
                }
            }
            twoFactorAuthEnabled = user.twoFactorAuthEnabled
        }

        return userRepository.save(existingUser).toDTO()
    }

    override fun delete(userId: Long) {
        userRepository.deleteById(userId)
    }
}
