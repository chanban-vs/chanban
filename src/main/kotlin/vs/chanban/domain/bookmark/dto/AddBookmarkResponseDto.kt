package vs.chanban.domain.bookmark.dto

import vs.chanban.domain.bookmark.Bookmark

class AddBookmarkResponseDto(
    val bookmarkId: Long,
    val topicId: Long
) {
    companion object {
        fun of(bookmark: Bookmark): AddBookmarkResponseDto = AddBookmarkResponseDto(
            bookmarkId = bookmark.bookmarkId!!,
            topicId = bookmark.topicId.topicId!!
        )
    }
}