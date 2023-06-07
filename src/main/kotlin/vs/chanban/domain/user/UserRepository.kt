package vs.chanban.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun findByUserEmail(email: String): Optional<User>
    fun existsByUserEmail(email: String): Boolean
}