package vs.chanban.controller.topic

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import vs.chanban.domain.enum.topic.TopicSubject
import vs.chanban.domain.support.util.ip.IpUtil
import vs.chanban.domain.topic.Topic
import vs.chanban.domain.topic.TopicService
import vs.chanban.domain.topic.dto.AddTopicRequestDto
import vs.chanban.domain.topic.dto.AddTopicResponseDto
import vs.chanban.domain.topic.dto.TopicPreviewResponseDto

@RestController
class TopicController(private val topicService: TopicService) {
    @PostMapping("/topic")
    fun addTopic(
        @RequestBody @Valid addTopicRequestDto: AddTopicRequestDto,
        request: HttpServletRequest
    ): ResponseEntity<AddTopicResponseDto> {
        addTopicRequestDto.userIp = IpUtil.getClientIpAddress(request)

        val topic =  topicService.addTopic(addTopicRequestDto)
        val addTopicResponseDto: AddTopicResponseDto = AddTopicResponseDto.of(topic)

        return ResponseEntity.ok(addTopicResponseDto)
    }

    @GetMapping("/topic/{topicId}")
    fun getTopic(@PathVariable topicId: Long): Topic {
        return topicService.getTopicById(topicId)
    }

    @GetMapping("/topics/{topicSubject}")
    fun getTopicsByTopicSubject(
        @PathVariable topicSubject: String,
        @RequestParam("page") page: Int?,
        @RequestParam("pageSize") pageSize: Int?
    ): Page<TopicPreviewResponseDto> {
        return topicService.getTopicsByTopicSubject(TopicSubject.of(topicSubject), page, pageSize)
    }
}