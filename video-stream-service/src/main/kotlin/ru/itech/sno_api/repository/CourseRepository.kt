package ru.itech.sno_api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import ru.itech.sno_api.entity.CourseEntity

@Repository
interface CourseRepository : JpaRepository<CourseEntity, Long>, JpaSpecificationExecutor<CourseEntity> {
    fun findByTitle(title: String): List<CourseEntity>
    fun findByDescriptionContaining(description: String): List<CourseEntity>
}
