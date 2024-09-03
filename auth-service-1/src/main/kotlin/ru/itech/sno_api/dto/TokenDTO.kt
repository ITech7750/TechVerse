package ru.itech.sno_api.dto

data class TokenDTO(
    val login: String,
    val token: String,
    val userId: Long
)
