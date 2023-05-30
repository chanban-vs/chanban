package vs.chanban.domain.enum.poll

import org.springframework.http.HttpStatus
import vs.chanban.common.Message
import vs.chanban.common.exception.ChanbanBizException
import vs.chanban.domain.enum.ChanbanEnum

enum class PollOption(private val description: String): ChanbanEnum {
    POSITIVE("찬성"), NEGATIVE("반대");

    override fun getName(): String {
        return name
    }
    override fun getDescription(): String {
        return description
    }

    companion object {
        fun of(name: String): PollOption {
            try {
                return PollOption.valueOf(name)
            } catch(ex: IllegalArgumentException) {
                val enumName = "pollOption"
                throw ChanbanBizException(HttpStatus.BAD_REQUEST, Message.Validation.INVALID_ENUM_VALUE.format(enumName, name))
            }
        }
    }
}