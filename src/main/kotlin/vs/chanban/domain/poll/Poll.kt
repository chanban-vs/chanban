package vs.chanban.domain.poll

import jakarta.persistence.*
import vs.chanban.domain.enum.poll.PollOption
import vs.chanban.domain.listener.BaseTimeListener
import vs.chanban.domain.topic.Topic

@Entity(name = "poll")
class Poll(topic: Topic, pollOption: PollOption, userIp: String): BaseTimeListener() {
    @Id
    @Column(name = "poll_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val pollId: Long? = null

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    val topicId: Topic? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "poll_answer", length = 20, nullable = false)
    val pollAnswer: PollOption? = null

    @Column(name = "user_ip", length = 20, nullable = false)
    val userIp: String = ""
}