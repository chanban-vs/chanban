package vs.chanban.domain.topic

import jakarta.persistence.*
import vs.chanban.domain.enum.topic.TopicSubject
import vs.chanban.domain.listener.BaseTimeListener

@Entity
@Table(name = "topic")
class Topic(
    @Id
    @Column(name = "topic_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val topicId: Long? = null,

    @Column(name = "topic_title", length = 50, nullable = false)
    val topicTitle: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "topic_subject", length = 20, nullable = false)
    val topicSubject: TopicSubject,

    @Column(name = "topic_content")
    val topicContent: String = "",

    @Column(name = "user_ip", length = 20, nullable = false)
    val userIp: String
): BaseTimeListener()