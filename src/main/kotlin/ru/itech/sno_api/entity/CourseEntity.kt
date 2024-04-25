package ru.itech.sno_api.entity

import jakarta.persistence.*
import ru.itech.sno_api.dto.CourseDTO
import java.util.*

@Entity
@Table(name = "course")
data class CourseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    var courseId: Long = 0,

    @OneToMany(mappedBy = "course", cascade = [CascadeType.ALL], orphanRemoval = true)
    val lectures: MutableList<LectureEntity> = mutableListOf(),

    @ManyToMany(mappedBy = "courses")
    val users: MutableSet<UserEntity> = mutableSetOf(),

    @ManyToOne
    @JoinColumn(name = "admin_id")
    var admin: UserEntity? = null,

    @Column(name = "title")
    var title: String = "",

    @Column(name = "description")
    var description: String = "",

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    var startDate: Date? = null,

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    var endDate: Date? = null
)
fun CourseEntity.toDTO(): CourseDTO {
    return CourseDTO(
        courseId = this.courseId,
        title = this.title,
        description = this.description,
        startDate = this.startDate,
        endDate = this.endDate,
        adminId = this.admin?.userId
    )
}
