package vs.chanban

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditin
class ChanBanApplication

fun main(args: Array<String>) {
    runApplication<ChanBanApplication>(*args)
}