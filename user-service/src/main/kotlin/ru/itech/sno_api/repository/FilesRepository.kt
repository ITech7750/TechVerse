package ru.itech.sno_api.repository


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.itech.sno_api.entity.FilesEntity
@Repository
interface FilesRepository : JpaRepository<FilesEntity, Long>
