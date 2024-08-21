package ru.itech.sno_api.repository



import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.itech.sno_api.entity.UserCourseEntity
import ru.itech.sno_api.entity.UserEntity
import ru.itech.sno_api.entity.CourseEntity

@Repository
interface UserCourseRepository : CrudRepository<UserCourseEntity, Long> {
    fun findByUser(user: UserEntity): List<UserCourseEntity>
    fun findByCourse(course: CourseEntity): List<UserCourseEntity>
    fun findByUserAndCourse(user: UserEntity, course: CourseEntity): UserCourseEntity?
}
