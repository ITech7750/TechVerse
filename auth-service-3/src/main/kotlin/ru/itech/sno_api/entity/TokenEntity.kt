package ru.itech.sno_api.entity
import jakarta.persistence.*

@Entity
@Table(name = "token")
data class TokenEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val login: String = "",

    val token: String = "",

    @Column(name = "user_id")
    val userId: Long = 0
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    lateinit var user: UserEntity
}