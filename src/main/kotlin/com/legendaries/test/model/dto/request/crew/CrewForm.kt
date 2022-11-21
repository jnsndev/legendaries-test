package com.legendaries.test.model.dto.request.crew

import com.fasterxml.jackson.annotation.JsonFormat
import com.legendaries.test.model.entity.Crew
import com.legendaries.test.model.entity.Team
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CrewForm(

    @field:NotNull(message = "소속팀을 선택해주세요.")
    val teamId: Long,

    @field:NotBlank(message = "이름을 입력해주세요.")
    val name: String,

    @field:NotNull(message = "생년월일을 입력해주세요.")
    @JsonFormat(pattern = "yyyyMMdd")
    val birthDate: LocalDate,

    // TODO: 번호 양식 포멧
    @field:NotBlank(message = "전화번호를 입력해주세요.")
    val phone: String,
) {

    fun toEntity(team: Team): Crew {
        return Crew(
            team = team,
            name = name,
            birthDate = birthDate,
            phone = phone
        )
    }
}