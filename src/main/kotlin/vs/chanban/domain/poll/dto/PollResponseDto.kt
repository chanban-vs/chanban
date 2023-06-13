package vs.chanban.domain.poll.dto

import vs.chanban.domain.enum.dto.ChanbanEnumDto
import vs.chanban.domain.poll.Poll

class PollResponseDto(
    val pollId: Long,
    val topicId: Long,
    val pollOption: ChanbanEnumDto
) {
    companion object {
        fun of(topicId: Long, poll: Poll): PollResponseDto = PollResponseDto(
            pollId = poll.pollId!!,
            topicId = topicId,
            pollOption = ChanbanEnumDto.of(poll.pollAnswer)
        )
    }
}