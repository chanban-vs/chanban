package vs.chanban.controller.enum

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import vs.chanban.domain.enum.EnumResponseDto
import vs.chanban.domain.enum.EnumService
import vs.chanban.domain.enum.poll.PollOption
import vs.chanban.domain.enum.topic.TopicSubject

@RestController
class EnumController(private val enumService: EnumService) {
    @GetMapping("/topic-subjects")
    fun getAllTopicSubjects(): List<EnumResponseDto> {
        return enumService.getAllEnumValues(TopicSubject::class.java)
    }

    @GetMapping("/poll-options")
    fun getAllPollOptions(): List<EnumResponseDto> {
        return enumService.getAllEnumValues(PollOption::class.java)
    }
}