package ru.itech.sno_api.repository


import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.itech.sno_api.entity.CourseLectureEntity
import ru.itech.sno_api.entity.CourseEntity
import ru.itech.sno_api.entity.LectureEntity

@Repository
interface CourseLectureRepository : CrudRepository<CourseLectureEntity, Long> {
    fun findByCourse(course: CourseEntity): List<CourseLectureEntity>
    fun findByLecture(lecture: LectureEntity): List<CourseLectureEntity>
    fun findByCourseAndLecture(course: CourseEntity, lecture: LectureEntity): CourseLectureEntity?
}
