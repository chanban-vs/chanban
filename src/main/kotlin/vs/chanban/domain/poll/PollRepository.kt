package vs.chanban.domain.poll

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import vs.chanban.domain.enum.poll.PollOption
import vs.chanban.domain.topic.Topic

@Repository
interface PollRepository: JpaRepository<Poll, Long> {
    fun countByTopicId(topicId: Topic): Long
    fun countByTopicIdAndPollAnswer(topicId: Topic, pollAnswer: PollOption): Long
}