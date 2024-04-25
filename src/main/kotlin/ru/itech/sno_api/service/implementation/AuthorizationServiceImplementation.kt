package ru.itech.sno_api.service.implementation

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import ru.itech.sno_api.dto.AuthorizationDTO
import ru.itech.sno_api.dto.toDTO
import ru.itech.sno_api.entity.AuthorizationEntity
import ru.itech.sno_api.entity.toEntity
import ru.itech.sno_api.repository.AuthorizationRepository
import ru.itech.sno_api.service.AuthorizationService

@Service
class AuthorizationServiceImplementation(
    private val authorizationRepository: AuthorizationRepository
): AuthorizationService {

    override fun getAll(): List<AuthorizationDTO> {
        return authorizationRepository.findAll()
            .map { it.toDTO() }
    }

    override fun getById(authId: Long): AuthorizationDTO {
        return authorizationRepository.findById(authId)
            .orElseThrow { throw EntityNotFoundException("Authorization with ID $authId not found") }
            .toDTO()
    }

    override fun create(authorization: AuthorizationDTO): AuthorizationDTO {
        return authorizationRepository.save(authorization.toEntity())
            .toDTO()
    }

    override fun update(authId: Long, authorization: AuthorizationDTO): AuthorizationDTO {
        val existingAuthorization = authorizationRepository.findById(authId)
            .orElseThrow { throw EntityNotFoundException("Authorization with ID $authId not found") }

        existingAuthorization.user = authorization.user.toEntity()
        existingAuthorization.token = authorization.token
        existingAuthorization.twoFactorEnabled = authorization.twoFactorEnabled

        return authorizationRepository.save(existingAuthorization)
            .toDTO()
    }

    override fun delete(authId: Long) {
        authorizationRepository.deleteById(authId)
    }
}

fun AuthorizationEntity.toDTO(): AuthorizationDTO {
    return AuthorizationDTO(
        authId = authId,
        user = user!!.toDTO(),
        token = token,
        twoFactorEnabled = twoFactorEnabled
    )
}

fun AuthorizationDTO.toEntity(): AuthorizationEntity {
    return AuthorizationEntity(
        authId = authId,
        user = user.toEntity(),
        token = token,
        twoFactorEnabled = twoFactorEnabled
    )
}
