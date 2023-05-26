package vs.chanban.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import vs.chanban.common.exception.ChanbanBizException

@RestController
class HelloController {
    @GetMapping("/hello")
    fun hello(): String {
        return "Hello"
    }

    @GetMapping("/error-test")
    fun errorTest(): String {
        throw ChanbanBizException(HttpStatus.BAD_REQUEST ,"error-test")
    }
}