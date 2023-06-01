package vs.chanban.domain.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import vs.chanban.domain.listener.BaseTimeListener

@Entity
@Table(name = "user")
class User(
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: Long? = null,

    @Column(name = "user_email", length = 320, nullable = false)
    val userEmail: String,

    @Column(name = "user_password", length = 100)
    val userPassword: String
): BaseTimeListener()