package vs.chanban.domain.comment

import jakarta.persistence.*
import vs.chanban.domain.enum.poll.PollOption
import vs.chanban.domain.listener.BaseTimeListener
import vs.chanban.domain.topic.Topic
import vs.chanban.domain.user.User

@Entity
@Table(name = "comment",
    indexes = [
        Index(columnList = "created_at", name = "comment_idx_1"),
        Index(columnList = "updated_at", name = "comment_idx_2"),
        Index(columnList = "topic_id", name = "comment_idx_3"),
        Index(columnList = "poll_answer", name = "comment_idx_4")
    ]
)
class Comment(
    @Id
    @Column(name = "comment_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val commentId: Long? = null,

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    val topicId: Topic,

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    val parentCommentId: Comment? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "poll_answer", length = 20, nullable = false)
    val pollAnswer: PollOption,

    @Column(name = "comment_content", nullable = false)
    val commentContent: String,

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    val createdBy: User
): BaseTimeListener()