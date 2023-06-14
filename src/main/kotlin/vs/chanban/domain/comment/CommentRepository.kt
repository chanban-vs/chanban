package vs.chanban.domain.comment

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import vs.chanban.domain.enum.poll.PollOption
import vs.chanban.domain.topic.Topic

@Repository
interface CommentRepository: JpaRepository<Comment, Long> {
    fun findAllByParentCommentIdOrderByCreatedAtDesc(parentComment: Comment, pageable: Pageable): Page<Comment>

    @Query("SELECT c FROM Comment c LEFT JOIN c.parentCommentId p WHERE c.topicId = :topicId AND c.parentCommentId IS NULL GROUP BY c.commentId ORDER BY COUNT(p.commentId) DESC")
    fun findCommentsByTopicIddAndParentCommentIdIsNullOrderByChildCommentCount(@Param("topicId") topicId: Topic, pageable: Pageable): Page<Comment>

    @Query("SELECT c FROM Comment c LEFT JOIN c.parentCommentId p WHERE c.topicId = :topicId AND c.pollAnswer = :pollAnswer GROUP BY c.commentId ORDER BY COUNT(p.commentId) DESC")
    fun findCommentsByTopicIdAndPollAnswerOrderByChildCommentCount(@Param("topicId") topicId: Topic, @Param("pollAnswer") pollAnswer: PollOption, pageable: Pageable): Page<Comment>
}