package vs.chanban.domain.bookmark

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vs.chanban.common.Message.Authentication.FORBIDDEN
import vs.chanban.common.exception.ChanbanBizException
import vs.chanban.configuration.PaginationConfig
import vs.chanban.domain.bookmark.dto.AddBookmarkRequestDto
import vs.chanban.domain.bookmark.dto.AddBookmarkResponseDto
import vs.chanban.domain.bookmark.dto.IsBookmarkedResponseDto
import vs.chanban.domain.topic.Topic
import vs.chanban.domain.topic.TopicService
import vs.chanban.domain.topic.dto.TopicPreviewResponseDto
import vs.chanban.domain.user.User

@Service
class BookmarkCombineService(
    private val bookmarkService: BookmarkService,
    private val topicService: TopicService,
    private val paginationConfig: PaginationConfig
) {
    @Transactional
    fun addBookmark(addBookmarkRequestDto: AddBookmarkRequestDto): AddBookmarkResponseDto {
        addBookmarkRequestDto.topic = topicService.getTopic(addBookmarkRequestDto.topicId!!)
        val bookmark: Bookmark = bookmarkService.addBookmark(Bookmark.of(addBookmarkRequestDto))

        return AddBookmarkResponseDto.of(bookmark)
    }

    fun getBookmarkPage(user: User, page: Int?, pageSize: Int?): Page<TopicPreviewResponseDto> {
        val currentPage = page ?: paginationConfig.defaultPage
        val currentPageSize = pageSize ?: paginationConfig.defaultPageSize

        val bookmarkPage: Page<Bookmark> = bookmarkService.getBookmarkPage(user, PageRequest.of(currentPage, currentPageSize))

        return bookmarkPage.map { bookmark ->
            TopicPreviewResponseDto.of(bookmark.topicId)
        }
    }

    fun isBookmarked(user: User, topicId: Long): IsBookmarkedResponseDto {
        val topic: Topic = topicService.getTopic(topicId)

        return IsBookmarkedResponseDto.of(bookmarkService.isBookmarked(user, topic))
    }

    @Transactional
    fun deleteBookmark(user: User, bookmarkId: Long) {
        val bookmark: Bookmark = bookmarkService.getBookmark(bookmarkId)

        // user only who added this bookmark is able to delete
        if (user == bookmark.createdBy) {
            bookmarkService.deleteBookmark(bookmarkId)
        } else {
            throw ChanbanBizException(HttpStatus.FORBIDDEN, FORBIDDEN)
        }

        return
    }
}