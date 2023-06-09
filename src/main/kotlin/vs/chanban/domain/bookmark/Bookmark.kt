package vs.chanban.domain.bookmark

import jakarta.persistence.*
import vs.chanban.domain.listener.BaseTimeListener
import vs.chanban.domain.topic.Topic
import vs.chanban.domain.user.User

@Entity
@Table(name = "bookmark",
    indexes = [
        Index(columnList = "created_at", name = "bookmark_idx_1"),
        Index(columnList = "updated_at", name = "bookmark_idx_2"),
        Index(columnList = "created_by", name = "bookmark_idx_3"),
    ]
)
class Bookmark(
    @Id
    @Column(name = "bookmark_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val bookmarkId: Long? = null,

    @ManyToOne
    @JoinColumn(name = "topic_id")
    val topicId: Topic,

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    val createdBy: User
): BaseTimeListener()