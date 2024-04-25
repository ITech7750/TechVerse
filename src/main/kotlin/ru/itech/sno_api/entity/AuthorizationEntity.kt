package ru.itech.sno_api.entity
import jakarta.persistence.*


@Entity
@Table(name = "authorization")
class AuthorizationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    val authId: Long = 0,
    @OneToOne
    @JoinColumn(name = "user_id")
    var user: UserInfoEntity? = null,
    @Column(name = "token")
    var token: String = "",
    @Column(name = "two_factor_enabled")
    var twoFactorEnabled: Boolean = false
)
