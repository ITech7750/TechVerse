package ru.itech.sno_api.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.itech.sno_api.entity.LectureEntity
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

@Repository
interface LectureRepository : JpaRepository<LectureEntity, Long>, JpaSpecificationExecutor<LectureEntity> {
    fun findByTitle(title: String): List<LectureEntity>
    fun findByOrderByLectureId(pageable: Pageable): List<LectureEntity>
    fun findByLecturerUserId(lecturerId: Long): List<LectureEntity>
}


