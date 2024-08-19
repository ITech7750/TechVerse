package ru.itech.sno_api.service

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

    fun updateFile(lectureId: Long, fileId: Long)
    fun updateCourse(lectureId: Long, courseId: Long)
    fun updateLecturer(lectureId: Long, lecturerId: Long)
    fun updateDescription(lectureId: Long, description: String)
    fun updateForum(lectureId: Long, forumId: Long)
}
