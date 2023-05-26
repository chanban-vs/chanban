package vs.chanban.common.exception

import com.google.common.base.Throwables
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import vs.chanban.common.error.ChanbanErrorResponse

@ControllerAdvice
class ChanbanExceptionHandler {
    private val logger: Logger = LoggerFactory.getLogger(ChanbanExceptionHandler::class.java)
    @ExceptionHandler(ChanbanBizException::class)
    fun handleBizException(ex: ChanbanBizException): ResponseEntity<Any> {
        val httpStatus = ex.httpStatus

        logger.warn("[{}] {}", httpStatus.reasonPhrase, ex.message)
        return ResponseEntity(
            ChanbanErrorResponse(httpStatus.value(), ex.message.toString()),
            httpStatus
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleInternalServerError(ex: Exception): ResponseEntity<Any> {
        val httpStatus = HttpStatus.INTERNAL_SERVER_ERROR
        val stackTrace = Throwables.getStackTraceAsString(ex)
        logger.error("[{}] {}", httpStatus.reasonPhrase, stackTrace)
        return ResponseEntity(
            ChanbanErrorResponse(httpStatus.value(), ex.message.toString()),
            httpStatus
        )
    }
}