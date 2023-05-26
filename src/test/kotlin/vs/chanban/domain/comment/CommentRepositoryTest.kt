package vs.chanban.domain.comment

import TestData.Comment.TEST_COMMENT
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CommentRepositoryTest(@Autowired val commentRepository: CommentRepository) {
    @Test
    @DisplayName("save comment")
    fun saveComment() {
        val savedComment = commentRepository.save(TEST_COMMENT)

        Assertions.assertEquals(TEST_COMMENT.parentCommentId, savedComment.parentCommentId)
        Assertions.assertEquals(TEST_COMMENT.commentContent, savedComment.commentContent)
        Assertions.assertEquals(TEST_COMMENT.userIp, savedComment.userIp)
    }
}