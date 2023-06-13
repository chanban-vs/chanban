package vs.chanban.controller.topic

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import vs.chanban.domain.enum.topic.TopicSubject
import vs.chanban.domain.token.TokenService
import vs.chanban.domain.topic.TopicCombineService
import vs.chanban.domain.topic.TopicService
import vs.chanban.domain.topic.dto.AddTopicRequestDto
import vs.chanban.domain.topic.dto.AddTopicResponseDto
import vs.chanban.domain.topic.dto.TopicPreviewResponseDto
import vs.chanban.domain.topic.dto.TopicResponseDto

@RestController
@RequestMapping("/topic")
class TopicController(
    private val topicCombineService: TopicCombineService,
    private val topicService: TopicService,
    private val tokenService: TokenService
) {
    @PostMapping("")
    fun addTopic(
        @RequestBody @Valid addTopicRequestDto: AddTopicRequestDto,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<AddTopicResponseDto> {
        addTopicRequestDto.createdBy = tokenService.getUserFromToken(httpServletRequest)

        return ResponseEntity.ok(topicCombineService.addTopic(addTopicRequestDto))
    }

    @GetMapping("/{topicId}")
    fun getTopic(@PathVariable topicId: Long): ResponseEntity<TopicResponseDto> {
        return ResponseEntity.ok(topicCombineService.getTopic(topicId))
    }

    @GetMapping("/subject/{topicSubject}")
    fun getTopicsByTopicSubject(
        @PathVariable topicSubject: String,
        @RequestParam("page") page: Int?,
        @RequestParam("pageSize") pageSize: Int?
    ): ResponseEntity<Page<TopicPreviewResponseDto>> {
        return ResponseEntity.ok(topicCombineService.getTopicPage(TopicSubject.of(topicSubject), page, pageSize))
    }
}