package com.legendaries.test.exception

import com.legendaries.test.model.dto.response.CodedExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class CodedExceptionAdvice {

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CodedException::class)
    fun codedException(e: CodedException): CodedExceptionResponse {
        return CodedExceptionResponse.fromCodedException(e)
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
        value = [
            BindException::class,
            MethodArgumentNotValidException::class,
            ConstraintViolationException::class
        ]
    )
    fun methodArgumentNotValidException(e: Exception): CodedExceptionResponse {


        val errorCode = when (e) {
            is BindException -> e.bindingResult.allErrors[0].defaultMessage
            is MethodArgumentNotValidException -> e.bindingResult.allErrors[0].defaultMessage
            is ConstraintViolationException -> e.constraintViolations.first().message
            else -> null
        }


        val codedException = CodedException(
            errorCode = Code.INVALID_REQUEST,
            errorMessage = errorCode.toString()
        )

        return this.codedException(codedException)

//        return ExceptionResponse.bindingExceptionResponse(e.bindingResult)
    }
}