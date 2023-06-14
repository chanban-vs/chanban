package vs.chanban.domain.bookmark

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vs.chanban.common.Message.Bookmark.BOOKMARK_NOT_FOUND
import vs.chanban.common.constant.Constant.Common.ID
import vs.chanban.common.exception.ChanbanBizException
import vs.chanban.domain.topic.Topic
import vs.chanban.domain.user.User

@Service
@Transactional
class BookmarkService(private val bookmarkRepository: BookmarkRepository) {
    fun addBookmark(bookmark: Bookmark): Bookmark {
        return bookmarkRepository.save(bookmark)
    }

    @Transactional(readOnly = true)
    fun getBookmark(bookmarkId: Long): Bookmark {
        return bookmarkRepository.findById(bookmarkId).orElseThrow {
            ChanbanBizException(HttpStatus.BAD_REQUEST, BOOKMARK_NOT_FOUND.format(ID, bookmarkId))
        }
    }

    @Transactional(readOnly = true)
    fun getBookmarkPage(user: User, pageable: Pageable): Page<Bookmark> {
        return bookmarkRepository.findBookmarksByCreatedByOrderByCreatedAtDesc(user, pageable)
    }

    @Transactional(readOnly = true)
    fun isBookmarked(user: User, topic: Topic): Boolean {
        return bookmarkRepository.existsByCreatedByAndTopicId(user, topic)
    }

    fun deleteBookmark(bookmarkId: Long) {
        bookmarkRepository.deleteById(bookmarkId)
    }
}