package ru.itech.sno_api.entity
import jakarta.persistence.*
import ru.itech.sno_api.dto.LectureDTO
import ru.itech.sno_api.dto.toEntity
import ru.itech.sno_api.service.implementation.toDTO
import ru.itech.sno_api.service.implementation.toEntity
import java.util.*


@Entity
@Table(name = "lecture")
class LectureEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    var lectureId: Long = 0,

    @ManyToOne
    @JoinColumn(name = "course_id")
    var course: CourseEntity? = null,

    @OneToOne
    @JoinColumn(name = "lecturer_id")
    var lecturer: UserEntity? = null,

    @Column(name = "title")
    var title: String = "",

    @Column(name = "description")
    var description: String = "",

    @Column(name = "date")
    var date: Date? = null,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "summary_id")
    var summary: SummaryEntity? = null,

    @OneToOne
    @JoinColumn(name = "forum_id")
    var forum: ForumEntity? = null,

    @OneToOne
    @JoinColumn(name = "file_id")
    var file: FilesEntity? = null
)


fun LectureEntity.toDTO(): LectureDTO {
    return LectureDTO(
        lectureId = lectureId,
        lecturer = lecturer?.toDTO(),
        title = title,
        description = description,
        date = date,
        summary = summary?.toDTO(),
        forum = forum?.toDTO(),
        file = file?.toDTO()
    )
}
