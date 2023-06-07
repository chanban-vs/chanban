package vs.chanban.domain.support.util.random

object RandomUtil {
    fun generateVerificationCode(length: Int): String {
        val allowedChars = ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }
}