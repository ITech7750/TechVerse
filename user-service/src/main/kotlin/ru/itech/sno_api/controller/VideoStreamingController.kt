package ru.itech.sno_api.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import ru.itech.sno_api.service.FilesService
import ru.itech.sno_api.dto.FilesDTO

@RestController
@RequestMapping("/api/v1/stream")
@Tag(
    name = "Stream API",
    description = "Управление трансляцией"
)
class VideoController(private val filesService: FilesService) {

    @GetMapping("/stream/{fileId}")
    fun streamVideo(@PathVariable fileId: Long): ResponseEntity<StreamingResponseBody> {
        val fileDto: FilesDTO
        try {
            fileDto = filesService.getById(fileId)
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }

        val path: Path = Paths.get(fileDto.filePath)
        if (!Files.exists(path)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }

        var contentType = Files.probeContentType(path)
        val videoLength = Files.size(path)

        val headers = HttpHeaders().apply {
            contentType = MediaType.parseMediaType(contentType ?: "video/mp4").toString()
            contentLength = videoLength
        }

        val responseBody = StreamingResponseBody { outputStream ->
            Files.newInputStream(path).use { inputStream ->
                inputStream.copyTo(outputStream, 1024)
            }
        }

        return ResponseEntity.ok()
            .headers(headers)
            .body(responseBody)
    }
}
