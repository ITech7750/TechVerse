package ru.itech.sno_api.entity
import jakarta.persistence.*
import ru.itech.sno_api.dto.UserInfoDTO

@Entity
@Table(name = "user_table")
data class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val userId: Long = 0,

    @Column(unique = true)
    var login: String = "",

    var password: String = "",

    var email: String = ""
)

