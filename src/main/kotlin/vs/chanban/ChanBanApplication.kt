package vs.chanban

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChanBanApplication

fun main(args: Array<String>) {
    runApplication<ChanBanApplication>(*args)
}