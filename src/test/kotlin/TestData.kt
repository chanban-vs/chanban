import TestData.Topic.TEST_TOPIC
import vs.chanban.domain.comment.Comment
import vs.chanban.domain.enum.poll.PollOption
import vs.chanban.domain.enum.topic.TopicSubject
import vs.chanban.domain.poll.Poll
import vs.chanban.domain.topic.Topic

class TestData {
    object Topic {
        @JvmField
        val TEST_TOPIC = Topic(
            topicTitle = "test title",
            topicSubject = TopicSubject.SPORTS,
            topicContent = "test content",
            userIp = "test ip"
        )
    }

    object Poll {
        @JvmField
        val TEST_POLL = Poll(
            topicId = TEST_TOPIC,
            pollAnswer = PollOption.POSITIVE,
            userIp = "test_ip"
        )
    }

    object Comment {
        @JvmField
        val TEST_COMMENT = Comment(
            parentCommentId = null,
            commentContent = "test content",
            userIp = "test ip"
        )
    }
}