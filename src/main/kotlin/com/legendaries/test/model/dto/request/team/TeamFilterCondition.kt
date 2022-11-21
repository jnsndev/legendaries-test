package com.legendaries.test.model.dto.request.team

import javax.validation.constraints.Size

class TeamFilterCondition(

    @field:Size(min = 2, max = 100, message = "팀명은 2자 이상 100자 이하로 입력해주세요.")
    val name: String? = null,
)