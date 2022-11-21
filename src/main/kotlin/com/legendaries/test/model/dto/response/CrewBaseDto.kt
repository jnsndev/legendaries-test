package com.legendaries.test.model.dto.response

import com.legendaries.test.model.entity.Crew
import java.time.LocalDate
import java.time.LocalDateTime

class CrewBaseDto(
    val id: Long,
    val name: String,
    val birthDate: LocalDate,
    val phone: String,
    val joiningDate: LocalDate,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime?
) {

    companion object {
        fun fromEntity(crew: Crew): CrewBaseDto =
            CrewBaseDto(
                id = crew.id!!,
                name = crew.name,
                birthDate = crew.birthDate,
                phone = crew.phone,
                joiningDate = crew.joiningDate!!,
                createdAt = crew.createdAt!!,
                modifiedAt = crew.modifiedAt,
            )
    }
}