package ru.itech.sno_api.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import ru.itech.sno_api.dto.CourseDTO
import java.time.LocalDate

@Entity
@Table(name = "course")
class CourseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    var courseId: Long = 0,

    @OneToMany(mappedBy = "course", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonIgnore
    var courseLectures: MutableSet<CourseLectureEntity> = mutableSetOf(),

    @OneToOne
    @JoinColumn(name = "admin_id")
    var admin: UserEntity? = null,

    @Column(name = "title")
    var title: String = "",

    @Column(name = "description")
    var description: String = "",

    @Column(name = "start_date")
    var startDate: LocalDate? = null,

    @Column(name = "end_date")
    var endDate: LocalDate? = null,

    @OneToMany(mappedBy = "course", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonIgnore
    var userCourses: MutableSet<UserCourseEntity> = mutableSetOf()
) {
    companion object {
        fun create(
            title: String,
            description: String,
            startDate: LocalDate?,
            endDate: LocalDate?,
            admin: UserEntity?
        ): CourseEntity {
            val course = CourseEntity()
            course.title = title
            course.description = description
            course.startDate = startDate
            course.endDate = endDate
            course.admin = admin
            return course
        }
    }
}


fun CourseEntity.toDTO(): CourseDTO = CourseDTO(
    courseId = this.courseId,
    title = this.title,
    description = this.description,
    startDate = this.startDate ?: LocalDate.now(),
    endDate = this.endDate ?: LocalDate.now(),
    adminId = this.admin?.userId,
    userIds = this.userCourses.map { it.user?.userId ?: 0 }.toSet(),
    lectureIds = this.courseLectures.map { it.lecture?.lectureId ?: 0 }.toSet()
)
