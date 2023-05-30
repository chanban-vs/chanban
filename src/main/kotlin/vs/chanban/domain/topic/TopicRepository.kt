package vs.chanban.domain.topic

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import vs.chanban.domain.enum.topic.TopicSubject

@Repository
interface TopicRepository: JpaRepository<Topic, Long> {
    fun findAllByTopicSubject(topicSubject: TopicSubject, pageable: Pageable): Page<Topic>
}