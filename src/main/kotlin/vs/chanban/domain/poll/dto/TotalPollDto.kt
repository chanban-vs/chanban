package vs.chanban.domain.poll.dto

class TotalPollDto(
    val topicId: Long,
    val totalPoll: Long,
) {
    companion object {
        fun of(topicId: Long, totalPoll: Long): TotalPollDto = TotalPollDto(
            topicId = topicId,
            totalPoll = totalPoll
        )
    }
}