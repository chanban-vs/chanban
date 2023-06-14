package vs.chanban.domain.bookmark.dto

class IsBookmarkedResponseDto(
    val isBookmarked: Boolean
) {
    companion object {
        fun of(isBookmarked: Boolean): IsBookmarkedResponseDto = IsBookmarkedResponseDto(
            isBookmarked = isBookmarked
        )
    }
}