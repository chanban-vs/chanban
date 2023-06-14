package vs.chanban.domain.bookmark

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import vs.chanban.domain.user.User

@Repository
interface BookmarkRepository: JpaRepository<Bookmark, Long> {
    fun findBookmarksByCreatedByOrderByCreatedAtDesc(createdBy: User, pageable: Pageable): Page<Bookmark>
}