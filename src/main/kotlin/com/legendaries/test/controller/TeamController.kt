package com.legendaries.test.controller

import com.legendaries.test.model.dto.request.TeamFilterCondition
import com.legendaries.test.model.dto.request.TeamForm
import com.legendaries.test.model.dto.response.Response
import com.legendaries.test.model.dto.response.TeamDetailsDto
import com.legendaries.test.model.dto.response.TeamDto
import com.legendaries.test.service.TeamService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/teams")
class TeamController(
    private val teamService: TeamService
) {

    /**
     * 팀 생성
     */
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun teamAdd(@RequestBody @Valid teamForm: TeamForm): Response<TeamDto> =
        Response(HttpStatus.CREATED, teamService.addTeam(teamForm))

    /**
     * 팀 목록 조회
     */
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    fun teamList(teamFilterCondition: TeamFilterCondition, pageable: Pageable): Response<Page<TeamDto>> =
        Response(HttpStatus.OK, teamService.findTeams(teamFilterCondition, pageable))

    /**
     * 팀 상세 조회
     */
    @GetMapping("/{teamId}")
    @ResponseStatus(HttpStatus.OK)
    fun teamDetails(@PathVariable teamId: Long): Response<TeamDetailsDto> =
        Response(HttpStatus.OK, teamService.findTeam(teamId))

    /**
     * 팀 정보 수정
     */
    @PutMapping("/{teamId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun teamModify(@PathVariable teamId: Long, @RequestBody @Valid teamForm: TeamForm): Response<TeamDto> =
        Response(HttpStatus.CREATED, teamService.modifyTeam(teamId, teamForm))

    /**
     * 팀 삭제
     */
    @DeleteMapping("/{teamId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun teamRemove(@PathVariable teamId: Long) = teamService.removeTeam(teamId)

}