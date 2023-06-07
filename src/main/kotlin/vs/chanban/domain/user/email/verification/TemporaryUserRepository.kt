package vs.chanban.domain.user.email.verification

import org.springframework.data.repository.CrudRepository

interface TemporaryUserRepository: CrudRepository<TemporaryUser, String> {
}