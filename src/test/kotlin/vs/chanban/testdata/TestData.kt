package vs.chanban.testdata

import vs.chanban.testdata.TestData.Topic.SPORTS_TOPIC
import vs.chanban.domain.comment.Comment
import vs.chanban.domain.enum.account.role.AccountRole
import vs.chanban.domain.enum.poll.PollOption
import vs.chanban.domain.enum.topic.TopicSubject
import vs.chanban.domain.poll.Poll
import vs.chanban.domain.topic.Topic
import vs.chanban.domain.user.User

class TestData {
    object Topic {
        @JvmField
        val NEW_TOPIC = Topic(
            topicTitle = "test title",
            topicSubject = TopicSubject.SPORTS,
            topicContent = "test content",
            userIp = "127.0.0.1"
        )

        @JvmField
        val SPORTS_TOPIC = Topic(
            topicId = 1L,
            topicTitle = "test title",
            topicSubject = TopicSubject.SPORTS,
            topicContent = "test content",
            userIp = "127.0.0.1"
        )

        @JvmField
        val ANOTHER_SPORTS_TOPIC = Topic(
                topicId = 2L,
                topicTitle = "test title",
                topicSubject = TopicSubject.SPORTS,
                topicContent = "test content",
                userIp = "127.0.0.1"
        )
    }

    object Poll {
        @JvmField
        val TEST_POLL = Poll(
            topicId = SPORTS_TOPIC,
            pollAnswer = PollOption.POSITIVE,
            userIp = "127.0.0.1"
        )
    }

    object Comment {
        @JvmField
        val TEST_COMMENT = Comment(
            parentCommentId = null,
            commentContent = "test content",
            pollAnswer = PollOption.POSITIVE,
            userIp = "127.0.0.1"
        )
    }

    object User {
        val NEW_USER = User(
            userEmail = "test@email",
            userPassword = "test password",
            userRoles = mutableSetOf(AccountRole.USER)
        )

        val TEST_USER = User(
            userId = 1L,
            userEmail = "test@email",
            userPassword = "test password",
            userRoles = mutableSetOf(AccountRole.USER)

        )
    }
    object IpAddress {
        const val TEST_IP_ADDRESS = "127.0.0.1"
    }
}