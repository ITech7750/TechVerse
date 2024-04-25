package ru.itech.sno_api.entity
import jakarta.persistence.*
import ru.itech.sno_api.dto.AuthorizationDTO


@Entity
@Table(name = "authorization")
class AuthorizationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    val authId: Long = 0,
    @OneToOne
    @JoinColumn(name = "user_id")
    var user: UserEntity? = null,
    @Column(name = "token")
    var token: String = "",
    @Column(name = "two_factor_enabled")
    var twoFactorEnabled: Boolean = false
)
{
    fun toDTO(): AuthorizationDTO = AuthorizationDTO(
        authId = this.authId,
        user = this.user?.toDTO() ?: throw IllegalArgumentException("User information is required"),
        token = this.token,
        twoFactorEnabled = this.twoFactorEnabled
    )
}