package com.legendaries.test.service

import com.legendaries.test.exception.Code
import com.legendaries.test.exception.CodedException
import com.legendaries.test.model.dto.request.TeamFilterCondition
import com.legendaries.test.model.dto.request.TeamForm
import com.legendaries.test.model.dto.response.TeamDetailsDto
import com.legendaries.test.model.dto.response.TeamDto
import com.legendaries.test.model.entity.Team
import com.legendaries.test.repository.TeamRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class TeamService(
    private val teamRepository: TeamRepository
) {

    @Transactional
    fun addTeam(teamForm: TeamForm): TeamDto {
        checkDuplicateTeamName(teamForm.name)
        return TeamDto.fromEntity(teamRepository.save(teamForm.toEntity()))
    }

    fun findTeams(teamFilterCondition: TeamFilterCondition, pageable: Pageable): Page<TeamDto> =
        teamRepository.findAllByTeamFilterCondition(teamFilterCondition, pageable)
            .map { team -> TeamDto.fromEntity(team) }


    fun findTeam(teamId: Long): TeamDetailsDto = TeamDetailsDto.fromEntity(checkExistingTeam(teamId))

    @Transactional
    fun modifyTeam(teamId: Long, teamForm: TeamForm): TeamDto {
        val findTeam = checkExistingTeam(teamId)
        checkDuplicateTeamName(teamForm.name)
        findTeam.update(teamForm.name, teamForm.maxCrewCount)
        teamRepository.saveAndFlush(findTeam)

        return TeamDto.fromEntity(findTeam)
    }

    @Transactional
    fun removeTeam(teamId: Long) {
        val findTeam = checkExistingTeam(teamId)
        if (findTeam.crewList.isNotEmpty()) {
            throw CodedException(Code.NOT_DELETE_TEAM)
        }
        teamRepository.delete(findTeam)
    }

    fun checkExistingTeam(teamId: Long): Team =
        teamRepository.findById(teamId).orElseThrow {
            CodedException(Code.NOT_FOUND_TEAM)
        }

    fun checkDuplicateTeamName(name: String) {
        if (teamRepository.findAllByName(name).isNotEmpty()) {
            throw CodedException(Code.TEAM_NAME_ALREADY_IN_USE)
        }
    }
}