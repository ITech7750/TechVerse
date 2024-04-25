package ru.itech.sno_api.service.implementation

import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import ru.itech.sno_api.dto.FilesDTO
import ru.itech.sno_api.entity.FilesEntity
import ru.itech.sno_api.repository.FilesRepository
import ru.itech.sno_api.service.FilesService
import java.io.File
import java.io.FileInputStream
import java.io.IOException

@Service
@Transactional
open class FilesServiceImplementation(
    private val filesRepository: FilesRepository
) : FilesService {

    override fun getAll(): List<FilesDTO> {
        return filesRepository.findAll().map { it.toDTO() }
    }

    override fun getById(fileId: Long): FilesDTO {
        val fileEntity = filesRepository.findById(fileId)
            .orElseThrow { EntityNotFoundException("File with ID $fileId not found") }
        return fileEntity.toDTO()
    }

    override fun create(file: FilesDTO): FilesDTO {
        val fileEntity = file.toEntity()
        val savedFileEntity = filesRepository.save(fileEntity)
        return savedFileEntity.toDTO()
    }

    override fun update(fileId: Long, file: FilesDTO): FilesDTO {
        val existingFile = filesRepository.findById(fileId)
            .orElseThrow { EntityNotFoundException("File with ID $fileId not found") }
        existingFile.filePath = file.filePath
        existingFile.fileType = file.fileType.toString()
        val updatedFile = filesRepository.save(existingFile)
        return updatedFile.toDTO()
    }

    override fun delete(fileId: Long) {
        if (!filesRepository.existsById(fileId)) {
            throw EntityNotFoundException("File with ID $fileId not found")
        }
        filesRepository.deleteById(fileId)
    }

    override fun getFile(fileId: Long): ByteArray {
        val fileEntity = filesRepository.findById(fileId)
            .orElseThrow { EntityNotFoundException("File with ID $fileId not found") }
        val file = File(fileEntity.filePath)
        return file.readBytes() // This assumes the file can fit into memory
    }

    override fun uploadFile(file: MultipartFile): FilesDTO {
        if (file.isEmpty) {
            throw IllegalArgumentException("Uploaded file is empty")
        }

        val directory = File("uploads")
        if (!directory.exists()) {
            directory.mkdirs()
        }

        val filePath = "${directory.absolutePath}/${file.originalFilename}"
        val destFile = File(filePath)
        try {
            file.transferTo(destFile)
            val filesEntity = FilesEntity(filePath = filePath, fileType = "")
            val savedFileEntity = filesRepository.save(filesEntity)
            return savedFileEntity.toDTO()
        } catch (e: IOException) {
            throw RuntimeException("Failed to upload file: ${e.message}", e)
        }
    }

    // Utility methods to convert between DTO and Entity
    private fun FilesEntity.toDTO(): FilesDTO {
        return FilesDTO(fileId = fileId, filePath = filePath, fileType =  fileType)
    }

    private fun FilesDTO.toEntity(): FilesEntity {
        return FilesEntity(fileId = fileId, filePath = filePath, fileType = fileType.toString())
    }
}
