package com.legendaries.test.repository

import com.legendaries.test.model.dto.request.TeamFilterCondition
import com.legendaries.test.model.entity.Team
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TeamCustomRepository {

    fun findAllByTeamFilterCondition(teamFilterCondition: TeamFilterCondition, pageable: Pageable): Page<Team>
//    fun findByTeamId(teamId: Long): Team
}