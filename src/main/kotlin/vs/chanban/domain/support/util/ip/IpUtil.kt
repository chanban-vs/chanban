package vs.chanban.domain.support.util.ip

import jakarta.servlet.http.HttpServletRequest

object IpUtil {
    // Retrieve client ip address from request header
    fun getClientIpAddress(request: HttpServletRequest): String {
        return request.getHeader("X-Forwarded-For") ?: request.remoteAddr
    }
}