package com.legendaries.test.exception

import org.springframework.http.HttpStatus

class CodedException(
    var errorCode: Code = Code.TEAM_NAME_ALREADY_IN_USE,
    var errorMessage: String? = "",
    var httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
) : RuntimeException() {

    constructor(errorCode: Code) : this() {
        this.errorCode = errorCode
        this.errorMessage = errorCode.message
    }
}