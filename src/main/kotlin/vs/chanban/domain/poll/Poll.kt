package vs.chanban.domain.poll

import jakarta.persistence.*
import vs.chanban.domain.enum.poll.PollOption
import vs.chanban.domain.listener.BaseTimeListener
import vs.chanban.domain.topic.Topic
import vs.chanban.domain.user.User

@Entity
@Table(name = "poll",
    indexes = [
        Index(columnList = "created_at", name = "poll_idx_1"),
        Index(columnList = "updated_at", name = "poll_idx_2"),
        Index(columnList = "topic_id", name = "poll_idx_3"),
        Index(columnList = "created_by", name = "poll_idx_4"),
        Index(columnList = "poll_answer", name = "poll_idx_5")
    ]
)
class Poll(
    @Id
    @Column(name = "poll_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val pollId: Long? = null,

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    val topicId: Topic,

    @Enumerated(EnumType.STRING)
    @Column(name = "poll_answer", length = 20, nullable = false)
    val pollAnswer: PollOption,

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    val createdBy: User
): BaseTimeListener()