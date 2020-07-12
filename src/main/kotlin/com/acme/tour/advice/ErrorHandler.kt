package com.acme.tour.advice

import com.acme.tour.exception.PromocaoNotFoundException
import com.acme.tour.model.ErrorMessage
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception
import java.util.logging.ErrorManager
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestControllerAdvice
class ErrorHandler {
    @ExceptionHandler(JsonParseException::class)
    fun JsonParseException(serveletRequest: HttpServletRequest,
                           servletResponse: HttpServletResponse,
                            exception: Exception): ResponseEntity<ErrorMessage>{
        return ResponseEntity<ErrorMessage>(ErrorMessage("Json ERROR", exception.message ?: "invalid json")
                ,HttpStatus.BAD_REQUEST)
    }
    fun PromocaoNotFoundExceptionHandler(serveletRequest: HttpServletRequest,
                                         servletResponse: HttpServletResponse,
                                         exception: Exception): ResponseEntity<ErrorMessage>{
        return ResponseEntity(ErrorMessage("Promocao Não Localizada", exception.message !!),HttpStatus.NOT_FOUND)
    }
}