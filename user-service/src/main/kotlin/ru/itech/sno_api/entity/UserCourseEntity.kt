package ru.itech.sno_api.entity

import jakarta.persistence.*
import ru.itech.sno_api.entity.CourseEntity
import ru.itech.sno_api.entity.UserEntity

import jakarta.persistence.*

@Entity
@Table(name = "user_course")
class UserCourseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: UserEntity? = null,

    @ManyToOne
    @JoinColumn(name = "course_id")
    var course: CourseEntity? = null
) {
    companion object {
        fun create(user: UserEntity, course: CourseEntity): UserCourseEntity {
            return UserCourseEntity(
                user = user,
                course = course
            )
        }
    }
}
