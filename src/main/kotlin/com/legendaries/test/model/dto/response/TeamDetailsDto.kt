package com.legendaries.test.model.dto.response

import com.legendaries.test.model.entity.Team
import java.time.LocalDateTime

class TeamDetailsDto(
    val id: Long,
    val name: String,
    val maxCrewCount: Int,
    val nowCrewCount: Int,
    val crewList: List<CrewDto>? = null,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime?,
) {

    // TODO: TeamDto와의 중복코드 제거, 효율적인 DTO 사용 찾기
    companion object {
        fun fromEntity(team: Team): TeamDetailsDto =
            TeamDetailsDto(
                id = team.id!!,
                name = team.name,
                maxCrewCount = team.maxCrewCount,
                nowCrewCount = team.crewList.size,
                crewList = team.crewList.map { crew -> CrewDto.fromEntity(crew) },
                createdAt = team.createdAt!!,
                modifiedAt = team.modifiedAt
            )
    }
}