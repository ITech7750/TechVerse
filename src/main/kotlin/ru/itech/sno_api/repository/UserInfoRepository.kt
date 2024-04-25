package ru.itech.sno_api.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.itech.sno_api.entity.UserInfoEntity
import java.util.*
@Repository
interface UserInfoRepository : CrudRepository<UserInfoEntity, Long> {


    fun findByOrderByUserId(pageable: Pageable): List<UserInfoEntity>

    fun save(entity: UserInfoEntity): UserInfoEntity

}


