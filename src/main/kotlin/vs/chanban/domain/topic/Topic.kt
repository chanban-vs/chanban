package vs.chanban.domain.topic

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import vs.chanban.domain.listener.BaseTimeListener

@Entity(name = "topic")
class Topic(topicTitle: String, topicContent: String, userIp: String) : BaseTimeListener() {
    @Id
    @Column(name = "topic_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val topicId: Long? = null

    @Column(name = "topic_title", length = 50, nullable = false)
    val topicTitle: String = ""

    @Column(name = "topic_content")
    val topicContent: String = ""

    @Column(name = "user_ip", length = 20, nullable = false)
    val userIp: String = ""
}