package vs.chanban.controller.comment

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import vs.chanban.domain.comment.CommentCombineService
import vs.chanban.domain.comment.dto.AddCommentRequestDto
import vs.chanban.domain.comment.dto.CommentResponseDto
import vs.chanban.domain.enum.poll.PollOption
import vs.chanban.domain.token.TokenService
import vs.chanban.domain.user.User

@RestController
@RequestMapping("/comment")
class CommentController(
    private val tokenService: TokenService,
    private val commentCombineService: CommentCombineService
) {
    @PostMapping("")
    fun addComment(
        @RequestBody @Valid addCommentRequestDto: AddCommentRequestDto,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<CommentResponseDto> {
        val user: User = tokenService.getUserFromToken(httpServletRequest)
        addCommentRequestDto.createdBy = user

        return ResponseEntity.ok(commentCombineService.addComment(addCommentRequestDto))
    }

    @GetMapping("/child/{commentId}")
    fun getChildComments(
        @PathVariable commentId: Long,
        @RequestParam("page") page: Int?,
        @RequestParam("pageSize") pageSize: Int?,
        httpServletRequest: HttpServletRequest): ResponseEntity<Page<CommentResponseDto>> {

        return ResponseEntity.ok(commentCombineService.getChildCommentPage(commentId, page, pageSize))
    }

    @GetMapping("/{topicId}")
    fun getComments(
        @PathVariable topicId: Long,
        @RequestParam("page") page: Int?,
        @RequestParam("pageSize") pageSize: Int?,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<Page<CommentResponseDto>> {
        return ResponseEntity.ok(commentCombineService.getCommentPage(topicId, page, pageSize))
    }

    @GetMapping("/{topicId}/{pollOption}")
    fun getCommentsByPollAnswer(
        @PathVariable topicId: Long,
        @PathVariable pollOption: String,
        @RequestParam("page") page: Int?,
        @RequestParam("pageSize") pageSize: Int?,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<Page<CommentResponseDto>> {
        return ResponseEntity.ok(commentCombineService.getCommentPageByPollAnswer(topicId, PollOption.of(pollOption), page, pageSize))
    }
}