package vs.chanban.domain.topic

import jakarta.persistence.*
import vs.chanban.domain.enum.topic.TopicSubject
import vs.chanban.domain.listener.BaseTimeListener
import vs.chanban.domain.topic.dto.AddTopicRequestDto
import vs.chanban.domain.user.User

@Entity
@Table(name = "topic",
    indexes = [
        Index(columnList = "created_at", name = "user_idx_1"),
        Index(columnList = "updated_at", name = "user_idx_2"),
        Index(columnList = "created_by", name = "user_idx_3")
    ]
)
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

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    val createdBy: User
): BaseTimeListener() {
    companion object {
        fun of(addTopicRequestDto: AddTopicRequestDto): Topic = Topic(
            topicTitle = addTopicRequestDto.topicTitle,
            topicSubject = TopicSubject.of(addTopicRequestDto.topicSubject),
            topicContent = addTopicRequestDto.topicContent,
            createdBy = addTopicRequestDto.createdBy!!
        )
    }
}