package vs.chanban.domain.topic.dto

import vs.chanban.domain.topic.Topic

class AddTopicResponseDto(
    val topicId: Long
) {
    companion object {
        fun of(topic: Topic): AddTopicResponseDto = AddTopicResponseDto(
            topicId = topic.topicId!!
        )
    }
}