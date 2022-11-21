package com.legendaries.test.service

import com.legendaries.test.exception.Code
import com.legendaries.test.exception.CodedException
import com.legendaries.test.model.dto.request.crew.CrewFilterCondition
import com.legendaries.test.model.dto.request.crew.CrewForm
import com.legendaries.test.model.dto.response.CrewDto
import com.legendaries.test.model.entity.Crew
import com.legendaries.test.repository.CrewRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CrewService(
    private val crewRepository: CrewRepository,
    private val teamService: TeamService
) {

    @Transactional
    fun addCrew(crewForm: CrewForm): CrewDto {
        val findTeam = teamService.checkExistingTeam(crewForm.teamId)
        // 팀 최대 인원 초과여부 확인
        checkCrewCountInTeam(findTeam.maxCrewCount, findTeam.crewList.size)
        val crew = crewRepository.save(crewForm.toEntity(findTeam))

        return CrewDto.fromEntity(crew)
    }

    fun findCrews(crewFilterCondition: CrewFilterCondition, pageable: Pageable): Page<CrewDto> =
        crewRepository.findAllByCrewFilterCondition(crewFilterCondition, pageable)
            .map { crew -> CrewDto.fromEntity(crew) }

    fun findCrew(crewId: Long): CrewDto = CrewDto.fromEntity(checkExistingCrew(crewId))

    @Transactional
    fun modifyCrew(crewId: Long, crewForm: CrewForm): CrewDto {
        val findCrew = checkExistingCrew(crewId)
        val findTeam = teamService.checkExistingTeam(crewForm.teamId)
        findCrew.update(findTeam, crewForm.name, crewForm.birthDate, crewForm.phone)
        crewRepository.saveAndFlush(findCrew)

        return CrewDto.fromEntity(findCrew)
    }

    @Transactional
    fun removeCrew(crewId: Long) {
        val findCrew = checkExistingCrew(crewId)
        crewRepository.delete(findCrew)
    }

    fun checkExistingCrew(crewId: Long): Crew =
        crewRepository.findById(crewId).orElseThrow {
            CodedException(Code.NOT_FOUND_CREW)
        }

    fun checkCrewCountInTeam(maxCrewCount: Int, nowCrewCount: Int) {
        if (maxCrewCount <= nowCrewCount) {
            throw CodedException(Code.EXCEED_CREW_COUNT)
        }
    }
}