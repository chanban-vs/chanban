package vs.chanban.domain.comment.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import vs.chanban.common.Message
import vs.chanban.domain.comment.Comment
import vs.chanban.domain.topic.Topic
import vs.chanban.domain.user.User

class AddCommentRequestDto(
    @field:NotNull(message = Message.Validation.CANNOT_BE_NULL)
    val topicId: Long? = null,
    // parent comment id could be null
    val parentCommentId: Long? = null,
    @field:NotBlank(message = Message.Validation.CANNOT_BE_BLANK)
    val comment: String? = null,
    @field:NotBlank(message = Message.Validation.CANNOT_BE_BLANK)
    val pollAnswer: String? = null,

    @field:JsonIgnore
    var createdBy: User? = null,
    @field:JsonIgnore
    var topic: Topic? = null,
    @field:JsonIgnore
    var parentComment: Comment? = null
)