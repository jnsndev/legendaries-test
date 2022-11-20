package com.legendaries.test.model.dto.response

import org.springframework.http.HttpStatus

class Response<T>(
    val code: HttpStatus,
    val data: T
)