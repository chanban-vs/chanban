package vs.chanban.domain.poll

import jakarta.persistence.*
import vs.chanban.domain.enum.poll.PollOption
import vs.chanban.domain.listener.BaseTimeListener
import vs.chanban.domain.topic.Topic

@Entity
@Table(name = "poll")
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

    @Column(name = "user_ip", length = 20, nullable = false)
    val userIp: String
): BaseTimeListener()