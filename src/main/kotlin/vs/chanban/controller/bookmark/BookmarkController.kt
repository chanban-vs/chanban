package vs.chanban.controller.bookmark

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import vs.chanban.domain.bookmark.BookmarkCombineService
import vs.chanban.domain.bookmark.dto.AddBookmarkRequestDto
import vs.chanban.domain.bookmark.dto.AddBookmarkResponseDto
import vs.chanban.domain.token.TokenService
import vs.chanban.domain.topic.dto.TopicPreviewResponseDto
import vs.chanban.domain.user.User

@RestController
@RequestMapping("/bookmark")
class BookmarkController(
    private val tokenService: TokenService,
    private val bookmarkCombineService: BookmarkCombineService
) {
    @PostMapping("")
    fun addBookmark(
        @RequestBody @Valid addBookmarkRequestDto: AddBookmarkRequestDto,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<AddBookmarkResponseDto> {
        addBookmarkRequestDto.createdBy= tokenService.getUserFromToken(httpServletRequest)
        return ResponseEntity.ok(bookmarkCombineService.addBookmark(addBookmarkRequestDto))
    }

    @GetMapping("")
    fun getBookmarks(
        @RequestParam("page") page: Int?,
        @RequestParam("pageSize") pageSize: Int?,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<Page<TopicPreviewResponseDto>> {
        val user: User =  tokenService.getUserFromToken(httpServletRequest)
        return ResponseEntity.ok(bookmarkCombineService.getBookmarkPage(user, page, pageSize))
    }
}