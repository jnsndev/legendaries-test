package com.legendaries.test.model.dto.response

import com.legendaries.test.model.entity.Team
import java.time.LocalDateTime

class TeamDto(
    val id: Long,
    val name: String,
    val maxCrewCount: Int,
    val nowCrewCount: Int,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime?,
) {

    companion object {
        fun fromEntity(team: Team): TeamDto =
            TeamDto(
                id = team.id!!,
                name = team.name,
                maxCrewCount = team.maxCrewCount,
                nowCrewCount = team.crewList.size,
                createdAt = team.createdAt!!,
                modifiedAt = team.modifiedAt
            )
    }
}