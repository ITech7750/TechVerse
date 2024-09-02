package ru.itech.sno_api.service


import org.springframework.stereotype.Service
import ru.itech.sno_api.dto.SummaryDTO

@Service
interface SummaryService {

    fun getAll(): List<SummaryDTO>

    fun getById(summaryId: Long): SummaryDTO

    fun create(summary: SummaryDTO): SummaryDTO

    fun update(summaryId: Long, summary: SummaryDTO): SummaryDTO

    fun delete(summaryId: Long)
}
