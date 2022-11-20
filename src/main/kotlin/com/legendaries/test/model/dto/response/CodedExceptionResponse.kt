package com.legendaries.test.model.dto.response

import com.legendaries.test.exception.CodedException

class CodedExceptionResponse(
    val errorCode: String,
    val errorMessage: String?
) {

    companion object {
        fun fromCodedException(codedException: CodedException) =
            CodedExceptionResponse(codedException.errorCode.name, codedException.errorMessage)
    }
}