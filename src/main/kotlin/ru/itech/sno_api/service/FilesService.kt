package ru.itech.sno_api.service


import org.springframework.web.multipart.MultipartFile
import ru.itech.sno_api.dto.FilesDTO

interface FilesService {
    fun getAll(): List<FilesDTO>
    fun getById(fileId: Long): FilesDTO
    fun create(file: FilesDTO): FilesDTO
    fun update(fileId: Long, file: FilesDTO): FilesDTO
    fun delete(fileId: Long)
    fun getFile(fileId: Long): ByteArray
    fun uploadFile(file: MultipartFile): FilesDTO
}