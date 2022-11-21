package com.legendaries.test.controller

import com.legendaries.test.model.dto.request.crew.CrewFilterCondition
import com.legendaries.test.model.dto.request.crew.CrewForm
import com.legendaries.test.model.dto.response.CrewDto
import com.legendaries.test.model.dto.response.Response
import com.legendaries.test.service.CrewService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Tag(name = "크루 기능 API")
@RestController
@RequestMapping("/crews")
class CrewController(
    private val crewService: CrewService
) {

    @Operation(summary = "크루 생성 API")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun crewAdd(@RequestBody @Valid crewForm: CrewForm): Response<CrewDto> =
        Response(HttpStatus.CREATED, crewService.addCrew(crewForm))

    @Operation(summary = "크루 목록 조회 API")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    fun crewList(@Valid crewFilterCondition: CrewFilterCondition, pageable: Pageable): Response<Page<CrewDto>> =
        Response(HttpStatus.OK, crewService.findCrews(crewFilterCondition, pageable))

    @Operation(summary = "크루 상세 조회 API")
    @GetMapping("/{crewId}")
    @ResponseStatus(HttpStatus.OK)
    fun crewDetails(@PathVariable crewId: Long): Response<CrewDto> =
        Response(HttpStatus.OK, crewService.findCrew(crewId))

    @Operation(summary = "크루 정보 수정 API")
    @PutMapping("/{crewId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun crewModify(@PathVariable crewId: Long, @RequestBody @Valid crewForm: CrewForm): Response<CrewDto> =
        Response(HttpStatus.CREATED, crewService.modifyCrew(crewId, crewForm))

    @Operation(summary = "크루 삭제 API")
    @DeleteMapping("/{crewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun crewRemove(@PathVariable crewId: Long) = crewService.removeCrew(crewId)

}