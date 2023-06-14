package vs.chanban.domain.bookmark

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vs.chanban.domain.user.User

@Service
@Transactional
class BookmarkService(private val bookmarkRepository: BookmarkRepository) {
    fun addBookmark(bookmark: Bookmark): Bookmark {
        return bookmarkRepository.save(bookmark)
    }

    fun getBookmarkPage(user: User, pageable: Pageable): Page<Bookmark> {
        return bookmarkRepository.findBookmarksByCreatedByOrderByCreatedAtDesc(user, pageable)
    }
}