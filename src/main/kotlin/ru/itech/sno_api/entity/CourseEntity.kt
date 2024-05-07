package ru.itech.sno_api.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import ru.itech.sno_api.dto.CourseDTO
import java.time.LocalDate

@Entity
@Table(name = "course")
data class CourseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    var courseId: Long = 0,

    @OneToMany(mappedBy = "course", cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JsonIgnore
    val lectures: MutableList<LectureEntity> = mutableListOf(),

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
)

fun CourseEntity.toDTO(): CourseDTO = CourseDTO(this)