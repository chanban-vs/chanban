package vs.chanban.domain.user

import jakarta.persistence.*
import vs.chanban.domain.enum.account.role.AccountRole
import vs.chanban.domain.listener.BaseTimeListener
import vs.chanban.domain.user.email.verification.TemporaryUser

@Entity
@Table(name = "user")
class User(
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: Long? = null,

    @Column(name = "user_email", unique = true, length = 320, nullable = false)
    val userEmail: String,

    @Column(name = "user_password", length = 100)
    val userPassword: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "user_roles", length = 300, nullable = false)
    val userRoles: MutableSet<AccountRole>
): BaseTimeListener() {
    companion object {
        fun of(temporaryUser: TemporaryUser): User = User(
            userEmail = temporaryUser.email,
            userPassword = temporaryUser.password,
            userRoles = temporaryUser.roles
        )
    }
}