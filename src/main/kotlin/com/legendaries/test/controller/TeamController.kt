package com.legendaries.test.controller

import com.legendaries.test.model.dto.request.team.TeamFilterCondition
import com.legendaries.test.model.dto.request.team.TeamForm
import com.legendaries.test.model.dto.response.Response
import com.legendaries.test.model.dto.response.TeamDetailsDto
import com.legendaries.test.model.dto.response.TeamDto
import com.legendaries.test.service.TeamService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Tag(name = "팀 기능 API")
@RestController
@RequestMapping("/teams")
class TeamController(
    private val teamService: TeamService
) {

    @Operation(summary = "팀 생성 API")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun teamAdd(@RequestBody @Valid teamForm: TeamForm): Response<TeamDto> =
        Response(HttpStatus.CREATED, teamService.addTeam(teamForm))

    @Operation(summary = "팀 목록 조회 API")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    fun teamList(@Valid teamFilterCondition: TeamFilterCondition, pageable: Pageable): Response<Page<TeamDto>> =
        Response(HttpStatus.OK, teamService.findTeams(teamFilterCondition, pageable))

    @Operation(summary = "팀 상세 조회 API")
    @GetMapping("/{teamId}")
    @ResponseStatus(HttpStatus.OK)
    fun teamDetails(@PathVariable teamId: Long): Response<TeamDetailsDto> =
        Response(HttpStatus.OK, teamService.findTeam(teamId))

    @Operation(summary = "팀 정보 수정 API")
    @PutMapping("/{teamId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun teamModify(@PathVariable teamId: Long, @RequestBody @Valid teamForm: TeamForm): Response<TeamDto> =
        Response(HttpStatus.CREATED, teamService.modifyTeam(teamId, teamForm))

    @Operation(summary = "팀 삭제 API")
    @DeleteMapping("/{teamId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun teamRemove(@PathVariable teamId: Long) = teamService.removeTeam(teamId)

}