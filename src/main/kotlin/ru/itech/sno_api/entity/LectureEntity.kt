package ru.itech.sno_api.entity

import jakarta.persistence.*
import ru.itech.sno_api.dto.LectureDTO

import java.util.*

@Entity
@Table(name = "lecture")
class LectureEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    var lectureId: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    var course: CourseEntity? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecturer_id")
    var lecturer: UserEntity? = null,

    @Column(name = "title", nullable = false)
    var title: String = "",

    @Column(name = "description", nullable = false)
    var description: String = "",

    @Column(name = "date")
    var date: Date? = null,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "summary_id")
    var summary: SummaryEntity? = null,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "forum_id")
    var forum: ForumEntity? = null,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "file_id")
    var file: FilesEntity? = null
)

fun LectureEntity.toDTO(): LectureDTO {
    return LectureDTO(
        lectureId = this.lectureId,
        courseId = this.course?.courseId,
        lecturer = this.lecturer?.toDTO(),
        title = this.title,
        description = this.description,
        date = this.date,
        summary = this.summary?.toDTO(),
        forum = this.forum?.toDTO(),
        file = this.file?.toDTO()
    )
}
