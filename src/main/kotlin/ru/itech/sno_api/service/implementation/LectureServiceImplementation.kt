package ru.itech.sno_api.service.implementation

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.itech.sno_api.dto.LectureDTO
import ru.itech.sno_api.dto.toEntity
import ru.itech.sno_api.entity.LectureEntity
import ru.itech.sno_api.entity.toDTO
import ru.itech.sno_api.repository.LectureRepository
import ru.itech.sno_api.service.LectureService

@Service
@Transactional
open class LectureServiceImplementation(
    private val lectureRepository: LectureRepository
): LectureService {

    override fun getAll(): List<LectureDTO> {
        return lectureRepository.findAll()
            .map { it.toDTO() }
    }

    override fun getById(lectureId: Long): LectureDTO {
        return lectureRepository.findById(lectureId)
            .orElseThrow { EntityNotFoundException("Lecture with ID $lectureId not found") }
            .toDTO()
    }

    override fun create(lecture: LectureDTO): LectureDTO {
        return lectureRepository.save(lecture.toEntity())
            .toDTO()
    }

    override fun update(lectureId: Long, lecture: LectureDTO): LectureDTO {
        val existingLecture = lectureRepository.findById(lectureId)
            .orElseThrow { EntityNotFoundException("Lecture with ID $lectureId not found") }

        existingLecture.title = lecture.title
        existingLecture.description = lecture.description
        existingLecture.date = lecture.date
        if (lecture.lecturer != null) existingLecture.lecturer = lecture.lecturer.toEntity()
        if (lecture.summary != null) existingLecture.summary = lecture.summary.toEntity()
        if (lecture.forum != null) existingLecture.forum = lecture.forum.toEntity()
        if (lecture.file != null) existingLecture.file = lecture.file.toEntity()

        return lectureRepository.save(existingLecture)
            .toDTO()
    }

    override fun delete(lectureId: Long) {
        lectureRepository.deleteById(lectureId)
    }

    override fun getAllPaginated(pageIndex: Int, pageSize: Int): List<LectureDTO> {
        return lectureRepository.findByOrderByLectureId(PageRequest.of(pageIndex, 2))
            .map { it.toDTO() }
    }
}
