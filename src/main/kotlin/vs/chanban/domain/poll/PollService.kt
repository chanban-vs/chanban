package vs.chanban.domain.poll

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vs.chanban.common.Message.Poll.POLL_NOT_FOUND
import vs.chanban.common.exception.ChanbanBizException
import vs.chanban.domain.enum.poll.PollOption
import vs.chanban.domain.topic.Topic

@Service
@Transactional
class PollService(private val pollRepository: PollRepository) {
    fun addPoll(poll: Poll): Poll {
        return pollRepository.save(poll)
    }

    fun updatePoll(poll: Poll): Poll {
        return pollRepository.save(poll)
    }

    fun getPoll(pollId: Long): Poll {
        return pollRepository.findById(pollId).orElseThrow {
            ChanbanBizException(HttpStatus.NOT_FOUND, POLL_NOT_FOUND)
        }
    }

    @Transactional(readOnly = true)
    fun getTotalPoll(topic: Topic): Long {
        return pollRepository.countByTopicId(topic)
    }

    @Transactional(readOnly = true)
    fun getPollResult(topic: Topic, pollOption: PollOption): Long {
        return pollRepository.countByTopicIdAndPollAnswer(topic, pollOption)
    }
}