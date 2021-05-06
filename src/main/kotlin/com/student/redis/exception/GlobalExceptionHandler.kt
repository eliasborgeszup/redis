package com.student.redis.exception

import com.student.redis.controller.customer.dtos.ErrorDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.*
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception
import java.util.*
import java.util.Objects.nonNull

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(DocumentAlreadyExistsException::class)
    fun handleIllegalArgumentException(exception: DocumentAlreadyExistsException): ErrorDto {
        log.info(exception.message)
        return ErrorDto("Documento existente.")
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(NotFoundException::class)
    fun handleIllegalArgumentException(exception: NotFoundException): ErrorDto {
        log.info(exception.message)
        return ErrorDto("Documento não encontrado.")
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(PaginationSizeLimitExceededException::class)
    fun handleIllegalArgumentException(exception: PaginationSizeLimitExceededException): ErrorDto {
        log.info(exception.message)
        return ErrorDto("Quantidade de paginas maior que o permitido.")
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleIllegalArgumentException(exception: MethodArgumentNotValidException): List<ErrorDto> {
        val errors: MutableList<ErrorDto> = mutableListOf()

        exception.bindingResult.allErrors.forEach {
            if (nonNull(it.codes)) {
                val messageDisplayed: StringBuilder = StringBuilder()

                errors.add(ErrorDto(messageDisplayed.append(it.defaultMessage).toString()))
            }
        }

        return errors
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleIllegalArgumentException(exception: Exception): ErrorDto {
        log.info(exception.message)
        return ErrorDto("Erro interno no servidor, contate o administrador do sistema. " + exception.message)
    }
}