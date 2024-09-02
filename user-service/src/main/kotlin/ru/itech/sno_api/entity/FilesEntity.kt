package ru.itech.sno_api.entity

import jakarta.persistence.*
import ru.itech.sno_api.dto.FilesDTO

@Entity
@Table(name = "files")
class FilesEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    val fileId: Long? = null,

    @Column(name = "file_path")
    var filePath: String = "",

    @JoinColumn(name = "file_type")
    var fileType: String = "",
) {
    fun toDTO() = FilesDTO(
        fileId = fileId,
        filePath = filePath,
        fileType = fileType
    )
}
