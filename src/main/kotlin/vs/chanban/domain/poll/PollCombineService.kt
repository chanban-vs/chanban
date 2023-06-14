package vs.chanban.domain.poll

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vs.chanban.common.Message.Authentication.FORBIDDEN
import vs.chanban.common.exception.ChanbanBizException
import vs.chanban.domain.enum.poll.PollOption
import vs.chanban.domain.poll.dto.*
import vs.chanban.domain.topic.Topic
import vs.chanban.domain.topic.TopicService

@Service
class PollCombineService(
    private val pollService: PollService,
    private val topicService: TopicService
) {
    // get number of polls
    fun getTotalPoll(topicId: Long): TotalPollDto {
        val topic: Topic = topicService.getTopic(topicId)
        val totalPoll: Long = pollService.getTotalPoll(topic)

        return TotalPollDto.of(topicId, totalPoll)
    }

    //get number of each poll answers
    fun getPollResult(topicId: Long): PollResultDto {
        val topic: Topic = topicService.getTopic(topicId)
        val positivePoll: Long = pollService.getPollResult(topic, PollOption.POSITIVE)
        val negativePoll: Long = pollService.getPollResult(topic, PollOption.NEGATIVE)

        return PollResultDto.of(topicId, positivePoll, negativePoll)
    }
    @Transactional
    fun addPoll(addPollRequestDto: AddPollRequestDto): PollResponseDto {
        val topic: Topic = topicService.getTopic(addPollRequestDto.topicId!!)
        addPollRequestDto.topic = topic

        val poll: Poll = pollService.addPoll(Poll.of(addPollRequestDto))

        return PollResponseDto.of(topic.topicId!!, poll)
    }

    // update poll answer
    @Transactional
    fun updatePoll(updatePollRequestDto: UpdatePollRequestDto): PollResponseDto {
        var poll: Poll = pollService.getPoll(updatePollRequestDto.pollId!!)

        // check createdBy
        if (poll.createdBy != updatePollRequestDto.createdBy) {
            throw ChanbanBizException(HttpStatus.FORBIDDEN, FORBIDDEN)
        }

        poll.pollAnswer = PollOption.of(updatePollRequestDto.pollAnswer!!)
        poll = pollService.updatePoll(poll)

        return PollResponseDto.of(poll.topicId.topicId!!, poll)
    }
}