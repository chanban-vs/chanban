package vs.chanban.domain.comment

import jakarta.persistence.*
import vs.chanban.domain.listener.BaseTimeListener

@Entity(name = "comment")
class Comment: BaseTimeListener() {
    @Id
    @Column(name = "comment_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val commentId: Long? = null

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    val parentCommentId: Comment? = null

    @Column(name = "comment_content")
    val commentContent: String = ""

    @Column(name = "userIp", length = 20, nullable = false)
    val userIp: String = ""
}