package ru.itech.sno_api.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.itech.sno_api.entity.ForumEntity

@Repository
interface ForumRepository : CrudRepository<ForumEntity, Long>{

}
