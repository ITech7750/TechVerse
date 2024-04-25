package ru.itech.sno_api.entity
import jakarta.persistence.*
import ru.itech.sno_api.dto.LectureDTO
import ru.itech.sno_api.dto.toEntity
import ru.itech.sno_api.service.implementation.toEntity
import java.util.*


@Entity
@Table(name = "lecture")
class LectureEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    val lectureId: Long = 0,
    @OneToOne
    @JoinColumn(name = "lecturer_id")
    var lecturer: UserInfoEntity = UserInfoEntity(),
    @Column(name = "title")
    var title: String = "",
    @Column(name = "description")
    var description: String = "",
    @Column(name = "date")
    var date: Date? = null,
    @OneToOne
    @JoinColumn(name = "summary_id")
    var summary: SummaryEntity = SummaryEntity(),
    @OneToOne
    @JoinColumn(name = "forum_id")
    var forum: ForumEntity = ForumEntity(),
    @OneToOne
    @JoinColumn(name = "file_id")
    var file: FilesEntity = FilesEntity(),
)

fun LectureDTO.toEntity(): LectureEntity {
    return LectureEntity(
        lectureId = lectureId,
        lecturer = lecturer.toEntity(),
        title = title,
        description = description,
        date = date,
        summary = summary.toEntity(),
        forum = forum.toEntity(),
        file = file.toEntity()
    )
}
