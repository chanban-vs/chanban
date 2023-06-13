package vs.chanban.controller.poll

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import vs.chanban.domain.poll.PollCombineService
import vs.chanban.domain.poll.dto.*
import vs.chanban.domain.token.TokenService
import vs.chanban.domain.user.User

@RestController
@RequestMapping("/poll")
class PollController(
    private val tokenService: TokenService,
    private val pollCombineService: PollCombineService
) {
    @GetMapping("/total/{topicId}")
    fun getTotal(@PathVariable topicId: Long): ResponseEntity<TotalPollDto> {
        return ResponseEntity.ok(pollCombineService.getTotalPoll(topicId))
    }

    @GetMapping("/{topicId}")
    fun getPollResult(@PathVariable topicId: Long): ResponseEntity<PollResultDto> {
        return ResponseEntity.ok(pollCombineService.getPollResult(topicId))
    }
    @PostMapping("")
    fun addPoll(
        @RequestBody @Valid addPollRequestDto: AddPollRequestDto,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<PollResponseDto> {
        val user: User = tokenService.getUserFromToken(httpServletRequest)
        addPollRequestDto.createdBy = user

        return ResponseEntity.ok(pollCombineService.addPoll(addPollRequestDto))
    }

    @PatchMapping("")
    fun updatePoll(
        @RequestBody @Valid updatePollRequestDto: UpdatePollRequestDto,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<PollResponseDto> {
        val user: User = tokenService.getUserFromToken(httpServletRequest)
        updatePollRequestDto.createdBy = user

        return ResponseEntity.ok(pollCombineService.updatePoll(updatePollRequestDto))
    }

}