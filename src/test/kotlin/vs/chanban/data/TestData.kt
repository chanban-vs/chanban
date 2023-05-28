import TestData.Topic.SAVED_TEST_TOPIC
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

        @JvmField
        val SAVED_TEST_TOPIC = Topic(
            topicId = 1L,
            topicTitle = "test title",
            topicSubject = TopicSubject.SPORTS,
            topicContent = "test content",
            userIp = "test ip"
        )
    }

    object Poll {
        @JvmField
        val TEST_POLL = Poll(
            topicId = SAVED_TEST_TOPIC,
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

    object IpAddress {
        const val TEST_IP_ADDRESS = "127.0.0.1"
    }
}