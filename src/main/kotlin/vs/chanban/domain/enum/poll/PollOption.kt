package vs.chanban.domain.enum.poll

import vs.chanban.domain.enum.ChanbanEnum

enum class PollOption(private val description: String): ChanbanEnum {
    POSITIVE("찬성"), NEGATIVE("반대");

    override fun getName(): String {
        return name
    }
    override fun getDescription(): String {
        return description
    }

}