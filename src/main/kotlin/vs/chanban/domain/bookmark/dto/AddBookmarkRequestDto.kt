package vs.chanban.domain.bookmark.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.validation.constraints.NotNull
import vs.chanban.common.Message
import vs.chanban.domain.topic.Topic
import vs.chanban.domain.user.User

class AddBookmarkRequestDto(
    @field:NotNull(message = Message.Validation.CANNOT_BE_NULL)
    val topicId: Long? = null,

    @field:JsonIgnore
    var topic: Topic? = null,
    @field:JsonIgnore
    var createdBy: User? = null
)