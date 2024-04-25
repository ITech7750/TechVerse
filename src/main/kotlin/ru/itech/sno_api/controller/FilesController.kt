package ru.itech.sno_api.controller

import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.multipart.MultipartFile
import ru.itech.sno_api.dto.FilesDTO
import ru.itech.sno_api.service.FilesService

@RestController
@RequestMapping("/api/v1/files")
class FilesController(private val filesService: FilesService) {

    @GetMapping
    fun getAllFiles(): ResponseEntity<List<FilesDTO>> {
        val files = filesService.getAll()
        return ResponseEntity(files, HttpStatus.OK)
    }

    @GetMapping("/{fileId}")
    fun getFileById(@PathVariable fileId: Long): ResponseEntity<FilesDTO> {
        val file = filesService.getById(fileId)
        return ResponseEntity(file, HttpStatus.OK)
    }

    @PostMapping
    fun createFile(@RequestBody file: FilesDTO): ResponseEntity<FilesDTO> {
        val createdFile = filesService.create(file)
        return ResponseEntity(createdFile, HttpStatus.CREATED)
    }

    @PutMapping("/{fileId}")
    fun updateFile(@PathVariable fileId: Long, @RequestBody file: FilesDTO): ResponseEntity<FilesDTO> {
        val updatedFile = filesService.update(fileId, file)
        return ResponseEntity(updatedFile, HttpStatus.OK)
    }

    @DeleteMapping("/{fileId}")
    fun deleteFile(@PathVariable fileId: Long): ResponseEntity<Void> {
        filesService.delete(fileId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping("/{fileId}/download")
    fun downloadFile(@PathVariable fileId: Long): ResponseEntity<ByteArray> {
        val fileContent = filesService.getFile(fileId)
        return ResponseEntity(fileContent, HttpStatus.OK)
    }

    @PostMapping("/upload")
    fun uploadFile(@RequestParam("file") file: MultipartFile): ResponseEntity<FilesDTO> {
        val uploadedFile = filesService.uploadFile(file)
        return ResponseEntity(uploadedFile, HttpStatus.CREATED)
    }


}
