package vs.chanban.domain.bookmark

import jakarta.persistence.*
import org.apache.catalina.User
import vs.chanban.domain.topic.Topic

@Entity
@Table(name = "bookmark")
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
    ) {
}