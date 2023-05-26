package vs.chanban.common.exception

import org.springframework.http.HttpStatus

class ChanbanBizException(val httpStatus: HttpStatus, message: String): RuntimeException(message)