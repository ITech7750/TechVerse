package ru.itech.sno_api.service

import org.springframework.stereotype.Component
import ru.itech.sno_api.dto.OrganizationDTO

@Component
interface OrganizationService {
    fun getAll(): List<OrganizationDTO>

    fun getById(organizationId: Long): OrganizationDTO

    fun create(organization: OrganizationDTO): OrganizationDTO

    fun update(organizationId: Long, organization: OrganizationDTO): OrganizationDTO

    fun delete(organizationId: Long)
}