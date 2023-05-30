package vs.chanban.domain.mail

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class MailService(private val javaMailSender: JavaMailSender) {
}