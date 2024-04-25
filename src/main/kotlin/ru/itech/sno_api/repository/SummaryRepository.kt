package ru.itech.sno_api.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.itech.sno_api.entity.SummaryEntity

@Repository
interface SummaryRepository : CrudRepository<SummaryEntity, Long>
