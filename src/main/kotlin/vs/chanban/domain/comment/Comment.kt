package vs.chanban.domain.comment

import jakarta.persistence.*
import vs.chanban.domain.listener.BaseTimeListener

@Entity
@Table(name = "comment")
class Comment(
    @Id
    @Column(name = "comment_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val commentId: Long? = null,

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    val parentCommentId: Comment? = null,

    @Column(name = "comment_content", nullable = false)
    val commentContent: String,

    @Column(name = "user_ip", length = 20, nullable = false)
    val userIp: String
): BaseTimeListener()