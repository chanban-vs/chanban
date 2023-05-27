package vs.chanban.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import vs.chanban.common.exception.ChanbanBizException
import vs.chanban.domain.support.util.ip.IpUtil

@RestController
class HelloController {
    @GetMapping("/hello")
    fun hello(): String {
        return "Hello"
    }

    @GetMapping("/error-test")
    fun errorTest(): String {
        throw ChanbanBizException(HttpStatus.BAD_REQUEST ,"Error test")
    }

    @GetMapping("/ip-test")
    fun retrieveIpAddressTest(request: HttpServletRequest): String {
        return IpUtil.getClientIpAddress(request)
    }
}