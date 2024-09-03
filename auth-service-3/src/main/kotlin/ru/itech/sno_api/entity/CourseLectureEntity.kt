package ru.itech.sno_api.entity




import jakarta.persistence.*
@Entity
@Table(name = "course_lectures")
class CourseLectureEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    var course: CourseEntity? = null,

    @ManyToOne
    @JoinColumn(name = "lecture_id", nullable = false)
    var lecture: LectureEntity? = null,

    @Column(name = "lecture_order", nullable = false)
    var lectureOrder: Int = 0 // типо порядок лекций, но нужно допилить
) {

    constructor() : this(0, null, null, 0)

    companion object {
        fun create(course: CourseEntity, lecture: LectureEntity, lectureOrder: Int): CourseLectureEntity {
            return CourseLectureEntity(
                course = course,
                lecture = lecture,
                lectureOrder = lectureOrder
            )
        }
    }

    //Для получения идентификатора лекции
    fun getLectureId(): Long? = lecture?.lectureId
}
