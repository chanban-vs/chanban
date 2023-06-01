package vs.chanban.domain.bookmark

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookmarkRepository: JpaRepository<Bookmark, Long> {
}