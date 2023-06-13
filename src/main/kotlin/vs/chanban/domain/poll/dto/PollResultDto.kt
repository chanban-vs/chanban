package vs.chanban.domain.poll.dto

class PollResultDto(
    val topicId: Long,
    val positive: Long,
    val negative: Long,
    val totalPoll: Long,
) {
    companion object {
        fun of(topicId: Long, positivePoll: Long, negativePoll: Long): PollResultDto = PollResultDto(
            topicId = topicId,
            positive = positivePoll,
            negative = negativePoll,
            totalPoll = positivePoll + negativePoll
        )
    }
}