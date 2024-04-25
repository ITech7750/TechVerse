package ru.itech.sno_api.repository


import org.springframework.data.jpa.repository.JpaRepository
import ru.itech.sno_api.entity.CourseEntity

interface CourseRepository : JpaRepository<CourseEntity, Long>
