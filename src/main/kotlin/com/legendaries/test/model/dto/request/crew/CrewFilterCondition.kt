package com.legendaries.test.model.dto.request.crew

import java.time.YearMonth
import javax.validation.constraints.Size

class CrewFilterCondition(

    @field:Size(min = 2, max = 100, message = "팀명 및 이름은 2자 이상 100자 이하로 입력해주세요.")
    val name: String? = null,

    val fromBirth: YearMonth? = null,

    val toBirth: YearMonth? = null,
)