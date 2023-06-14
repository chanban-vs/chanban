package vs.chanban.domain.bookmark

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vs.chanban.configuration.PaginationConfig
import vs.chanban.domain.bookmark.dto.AddBookmarkRequestDto
import vs.chanban.domain.bookmark.dto.AddBookmarkResponseDto
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

    @Transactional
    fun getBookmarkPage(user: User, page: Int?, pageSize: Int?): Page<TopicPreviewResponseDto> {
        val currentPage = page ?: paginationConfig.defaultPage
        val currentPageSize = pageSize ?: paginationConfig.defaultPageSize

        val bookmarkPage: Page<Bookmark> = bookmarkService.getBookmarkPage(user, PageRequest.of(currentPage, currentPageSize))

        return bookmarkPage.map { bookmark ->
            TopicPreviewResponseDto.of(bookmark.topicId)
        }
    }
}