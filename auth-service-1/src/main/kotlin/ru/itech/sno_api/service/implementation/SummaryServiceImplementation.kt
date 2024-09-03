package ru.itech.sno_api.service.implementation

import jakarta.persistence.EntityNotFoundException
import ru.itech.sno_api.dto.SummaryDTO
import ru.itech.sno_api.entity.SummaryEntity
import org.springframework.stereotype.Service
import ru.itech.sno_api.repository.SummaryRepository
import ru.itech.sno_api.service.SummaryService

@Service
class SummaryServiceImplementation(
    private val summaryRepository: SummaryRepository
): SummaryService {

    override fun getAll(): List<SummaryDTO> {
        return summaryRepository.findAll()
            .map { it.toDTO() }
    }

    override fun getById(summaryId: Long): SummaryDTO {
        return summaryRepository.findById(summaryId)
            .orElseThrow { throw EntityNotFoundException("Summary with ID $summaryId not found") }
            .toDTO()
    }

    override fun create(summary: SummaryDTO): SummaryDTO {
        return summaryRepository.save(summary.toEntity())
            .toDTO()
    }

    override fun update(summaryId: Long, summary: SummaryDTO): SummaryDTO {
        val existingSummary = summaryRepository.findById(summaryId)
            .orElseThrow { throw EntityNotFoundException("Summary with ID $summaryId not found") }

        existingSummary.title = summary.title
        existingSummary.description = summary.description

        return summaryRepository.save(existingSummary)
            .toDTO()
    }

    override fun delete(summaryId: Long) {
        summaryRepository.deleteById(summaryId)
    }
}

fun SummaryEntity.toDTO(): SummaryDTO {
    return SummaryDTO(
        summaryId = summaryId,
        title = title,
        description = description
    )
}

fun SummaryDTO.toEntity(): SummaryEntity {
    return SummaryEntity(
        summaryId = summaryId,
        title = title,
        description = description
    )
}
