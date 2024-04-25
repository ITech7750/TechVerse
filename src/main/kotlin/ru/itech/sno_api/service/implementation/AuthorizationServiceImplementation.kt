package ru.itech.sno_api.service.implementation

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.itech.sno_api.dto.AuthorizationDTO
import ru.itech.sno_api.dto.toEntity
import ru.itech.sno_api.entity.AuthorizationEntity
import ru.itech.sno_api.repository.AuthorizationRepository
import ru.itech.sno_api.repository.UserInfoRepository
import ru.itech.sno_api.service.AuthorizationService

@Service
@Transactional
open class AuthorizationServiceImplementation(
    private val authorizationRepository: AuthorizationRepository,
    private val userInfoRepository: UserInfoRepository // Assuming you need to fetch user info entities
) : AuthorizationService {

    override fun getAll(): List<AuthorizationDTO> {
        return authorizationRepository.findAll().map { it.toDTO() }
    }

    override fun getById(authId: Long): AuthorizationDTO {
        val authorization = authorizationRepository.findById(authId)
            .orElseThrow { EntityNotFoundException("Authorization with ID $authId not found") }
        return authorization.toDTO()
    }

    override fun create(authorization: AuthorizationDTO): AuthorizationDTO {
        val entity = authorization.toEntity()
        return authorizationRepository.save(entity).toDTO()
    }

    override fun update(authId: Long, authorization: AuthorizationDTO): AuthorizationDTO {
        val existingAuthorization = authorizationRepository.findById(authId)
            .orElseThrow { EntityNotFoundException("Authorization with ID $authId not found") }

        existingAuthorization.apply {
            user = userInfoRepository.findById(authorization.user.userId)
                .orElseThrow { EntityNotFoundException("User with ID ${authorization.user.userId} not found") }
            token = authorization.token
            twoFactorEnabled = authorization.twoFactorEnabled
        }

        return authorizationRepository.save(existingAuthorization).toDTO()
    }

    override fun delete(authId: Long) {
        authorizationRepository.deleteById(authId)
    }
}
