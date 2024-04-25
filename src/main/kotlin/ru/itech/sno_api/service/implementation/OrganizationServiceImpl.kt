package ru.itech.sno_api.service.implementation




import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import ru.itech.sno_api.dto.OrganizationDTO
import ru.itech.sno_api.repository.OrganizationRepository
import ru.itech.sno_api.service.OrganizationService

@Service
class OrganizationServiceImplementation1(
    private val organizationRepository: OrganizationRepository
): OrganizationService {

    override fun getAll(): List<OrganizationDTO> {
        return organizationRepository.findAll()
            .map { it.toDTO() }
    }

    override fun getById(organizationId: Long): OrganizationDTO {
        return organizationRepository.findById(organizationId)
            .orElseThrow { throw EntityNotFoundException("Organization with ID $organizationId not found") }
            .toDTO()
    }

    override fun create(organization: OrganizationDTO): OrganizationDTO {
        return organizationRepository.save(organization.toEntity())
            .toDTO()
    }

    override fun update(organizationId: Long, organization: OrganizationDTO): OrganizationDTO {
        val existingOrganization = organizationRepository.findById(organizationId)
            .orElseThrow { throw EntityNotFoundException("Organization with ID $organizationId not found") }

        existingOrganization.university = organization.university
        existingOrganization.faculty = organization.faculty
        existingOrganization.groupName = organization.groupName
        existingOrganization.isSoftDeleted = organization.isSoftDeleted

        return organizationRepository.save(existingOrganization)
            .toDTO()
    }

    override fun delete(organizationId: Long) {
        organizationRepository.deleteById(organizationId)
    }
}


