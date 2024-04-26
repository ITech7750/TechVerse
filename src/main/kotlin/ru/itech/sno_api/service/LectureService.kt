package ru.itech.sno_api.service

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import ru.itech.sno_api.dto.LectureDTO

@Service
interface LectureService {

    fun getAll(): List<LectureDTO>

    fun getById(lectureId: Long): LectureDTO

    fun create(lecture: LectureDTO): LectureDTO

    fun update(lectureId: Long, lecture: LectureDTO): LectureDTO

    fun delete(lectureId: Long)

    fun getAllPaginated(pageIndex: Int, pageSize: Int): List<LectureDTO>

    fun findByTitle(title: String): List<LectureDTO>

    fun findByLecturer(lecturerId: Long): List<LectureDTO>
}
