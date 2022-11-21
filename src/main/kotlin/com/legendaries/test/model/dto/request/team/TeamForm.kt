package com.legendaries.test.model.dto.request.team

import com.legendaries.test.model.entity.Team
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class TeamForm(

    @field:NotBlank(message = "팀명을 입력해주세요.")
    val name: String,

    @field:NotNull(message = "최대 인원 수를 입력해주세요.")
    val maxCrewCount: Int,
) {

    fun toEntity(): Team =
        Team(
            name = name,
            maxCrewCount = maxCrewCount,
        )
}