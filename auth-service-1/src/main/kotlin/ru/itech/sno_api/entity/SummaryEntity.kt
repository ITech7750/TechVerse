package ru.itech.sno_api.entity
import jakarta.persistence.*
import ru.itech.sno_api.dto.SummaryDTO

@Entity
@Table(name = "summary")
class SummaryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "summary_id")
    var summaryId: Long = 0,
    @Column(name = "title")
    var title: String = "",
    @Column(name = "description")
    var description: String = "",
    @OneToOne(mappedBy = "summary", fetch = FetchType.LAZY)
    var lecture: LectureEntity? = null
)

fun SummaryEntity.toDTO(): SummaryDTO {
    return SummaryDTO(
        summaryId = this.summaryId,
        title = this.title,
        description = this.description
    )
}