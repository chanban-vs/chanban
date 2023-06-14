package vs.chanban.domain.comment.dto

import vs.chanban.domain.comment.Comment
import vs.chanban.domain.enum.dto.ChanbanEnumDto

class CommentResponseDto(
    val topicId: Long,
    val commentId: Long,
    val parentCommentId: Long? = null,
    val comment: String,
    val pollAnswer: ChanbanEnumDto
) {
    companion object {
        fun of(comment: Comment): CommentResponseDto {
            var parentCommentId: Long? = null
            if (null != comment.parentCommentId) {
                parentCommentId = comment.parentCommentId.commentId
            }
            return CommentResponseDto(
                commentId = comment.commentId!!,
                topicId = comment.topicId.topicId!!,
                parentCommentId = parentCommentId,
                comment = comment.commentContent,
                pollAnswer = ChanbanEnumDto.of(comment.pollAnswer)
            )
        }
    }
}