package vs.chanban.domain.comment

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vs.chanban.common.Message.Comment.COMMENT_NOT_FOUND
import vs.chanban.common.constant.Constant.Common.ID
import vs.chanban.common.exception.ChanbanBizException
import vs.chanban.domain.enum.poll.PollOption
import vs.chanban.domain.topic.Topic

@Service
@Transactional
class CommentService(private val commentRepository: CommentRepository) {
    fun addComment(comment: Comment): Comment {
        return commentRepository.save(comment)
    }

    @Transactional(readOnly = true)
    fun getComment(commentId: Long): Comment {
        return commentRepository.findById(commentId).orElseThrow {
            ChanbanBizException(HttpStatus.NOT_FOUND, COMMENT_NOT_FOUND.format(ID, commentId))
        }
    }

    @Transactional(readOnly = true)
    fun getChildCommentPage(parentComment: Comment, pageable: Pageable): Page<Comment> {
        return commentRepository.findAllByParentCommentIdOrderByCreatedAtDesc(parentComment, pageable)
    }

    //get comments by topic and poll answer order by child comment count
    @Transactional(readOnly = true)
    fun getCommentPageByPollAnswer(topic: Topic, pollOption: PollOption, pageable: Pageable): Page<Comment> {
        return commentRepository.findCommentsByTopicIdAndPollAnswerOrderByChildCommentCount(topic, pollOption, pageable)
    }

    // get comments by topic sorted by child comment count
    // only 1st layer comments (has no parent)
    @Transactional(readOnly = true)
    fun getCommentPage(topic: Topic, pageable: Pageable): Page<Comment> {
        return commentRepository.findCommentsByTopicIddAndParentCommentIdIsNullOrderByChildCommentCount(topic, pageable)
    }
}