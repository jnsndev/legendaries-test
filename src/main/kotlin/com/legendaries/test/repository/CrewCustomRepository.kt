package com.legendaries.test.repository

import com.legendaries.test.model.dto.request.crew.CrewFilterCondition
import com.legendaries.test.model.dto.request.team.TeamFilterCondition
import com.legendaries.test.model.entity.Crew
import com.legendaries.test.model.entity.Team
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CrewCustomRepository {

    fun findAllByCrewFilterCondition(crewFilterCondition: CrewFilterCondition, pageable: Pageable): Page<Crew>
//    fun findByTeamId(teamId: Long): Team
}