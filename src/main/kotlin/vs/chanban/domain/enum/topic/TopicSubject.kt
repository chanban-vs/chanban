package vs.chanban.domain.enum.topic

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
}