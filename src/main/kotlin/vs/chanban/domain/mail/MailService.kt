package vs.chanban.domain.mail

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import vs.chanban.common.Message.Mail.VERIFY_SUBJECT

@Service
class MailService(
    private val javaMailSender: JavaMailSender,
    private val templateEngine: TemplateEngine
) {
    suspend fun sendVerifyMail(email: String, verificationCode: String) {
        withContext(Dispatchers.IO) {
            val message = javaMailSender.createMimeMessage()
            val helper = MimeMessageHelper(message, false, "UTF-8")
            helper.setTo(email)
            helper.setSubject(VERIFY_SUBJECT)

            val context = Context()
            context.setVariable("code", verificationCode)

            val emailContent = templateEngine.process("verification-code-form", context)
            helper.setText(emailContent, true)

            javaMailSender.send(message)
        }
    }


}