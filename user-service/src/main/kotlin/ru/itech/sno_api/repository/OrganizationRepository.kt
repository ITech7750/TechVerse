package ru.itech.sno_api.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.itech.sno_api.entity.OrganizationEntity
@Repository
interface OrganizationRepository : CrudRepository<OrganizationEntity, Long>
