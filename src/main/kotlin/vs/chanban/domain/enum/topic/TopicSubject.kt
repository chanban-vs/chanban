package vs.chanban.domain.enum.topic

import org.springframework.http.HttpStatus
import vs.chanban.common.Message.Validation.INVALID_ENUM_VALUE
import vs.chanban.common.exception.ChanbanBizException
import vs.chanban.domain.enum.ChanbanEnum

enum class TopicSubject(private val description: String): ChanbanEnum {
    SELF_DIAGNOSIS("자가진단"),
    INVESTMENT("투자"),
    ENTERTAINMENTS("연예"),
    SPORTS("스포츠"),
    POLITICS("정치"),
    RELATIONSHIPS("인간관계"),
    GIBBERISH("횡설수설");

    override fun getName(): String {
        return name
    }
    override fun getDescription(): String {
        return description
    }

    companion object {
        fun of(name: String): TopicSubject {
            try {
                return TopicSubject.valueOf(name)
            } catch(ex: IllegalArgumentException) {
                val enumName = "topicSubject"
                throw ChanbanBizException(HttpStatus.BAD_REQUEST, INVALID_ENUM_VALUE.format(enumName, name))
            }
        }
    }
}