package vs.chanban.domain.comment

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vs.chanban.common.Message.Comment.INVALID_PARENT_COMMENT_BY_PARENT_COMMENT
import vs.chanban.common.exception.ChanbanBizException
import vs.chanban.configuration.PaginationConfig
import vs.chanban.domain.comment.dto.AddCommentRequestDto
import vs.chanban.domain.comment.dto.CommentResponseDto
import vs.chanban.domain.enum.poll.PollOption
import vs.chanban.domain.topic.Topic
import vs.chanban.domain.topic.TopicService

@Service
class CommentCombineService(
    private val commentService: CommentService,
    private val topicService: TopicService,
    private val paginationConfig: PaginationConfig
) {
    @Transactional
    fun addComment(addCommentRequestDto: AddCommentRequestDto): CommentResponseDto {
        // get parent comment if exists
        if (null != addCommentRequestDto.parentCommentId) {
            val parentComment: Comment = commentService.getComment(addCommentRequestDto.parentCommentId)

            // parent comment should not have its parent comment
            // comments should not exceed 2 layers
            if (null != parentComment.parentCommentId) {
                throw ChanbanBizException(HttpStatus.BAD_REQUEST, INVALID_PARENT_COMMENT_BY_PARENT_COMMENT)
            }

            addCommentRequestDto.parentComment = parentComment
        }

        // get topic by topic id
        addCommentRequestDto.topic = topicService.getTopic(addCommentRequestDto.topicId!!)

        val comment: Comment = commentService.addComment(Comment.of(addCommentRequestDto))

        return CommentResponseDto.of(comment)
    }

    // get comments by parent comment
    fun getChildCommentPage(parentCommentId: Long, page: Int?, pageSize: Int?): Page<CommentResponseDto> {
        val parentComment: Comment = commentService.getComment(parentCommentId)
        val currentPage = page ?: paginationConfig.defaultPage
        val currentPageSize = pageSize ?: paginationConfig.defaultPageSize

        val commentPage: Page<Comment> = commentService.getChildCommentPage(parentComment, PageRequest.of(currentPage, currentPageSize))

        return commentPage.map { comment ->
            CommentResponseDto.of(comment)
        }
    }

    // get comments by topic
    fun getCommentPage(topicId: Long, page: Int?, pageSize: Int?): Page<CommentResponseDto> {
        val topic: Topic = topicService.getTopic(topicId)
        val currentPage = page ?: paginationConfig.defaultPage
        val currentPageSize = pageSize ?: paginationConfig.defaultPageSize

        val commentPage: Page<Comment> = commentService.getCommentPage(topic, PageRequest.of(currentPage, currentPageSize))

        return commentPage.map { comment ->
            CommentResponseDto.of(comment)
        }
    }

    // get positive or negative comments
    fun getCommentPageByPollAnswer(topicId: Long, pollOption: PollOption, page: Int?, pageSize: Int?): Page<CommentResponseDto> {
        val topic: Topic = topicService.getTopic(topicId)
        val currentPage = page ?: paginationConfig.defaultPage
        val currentPageSize = pageSize ?: paginationConfig.defaultPageSize

        val commentPage: Page<Comment> = commentService.getCommentPageByPollAnswer(topic, pollOption, PageRequest.of(currentPage, currentPageSize))

        return commentPage.map { comment ->
            CommentResponseDto.of(comment)
        }
    }
}