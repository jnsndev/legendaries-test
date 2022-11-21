package com.legendaries.test.repository

import com.legendaries.test.model.entity.Crew
import com.legendaries.test.model.entity.Team
import org.springframework.data.jpa.repository.JpaRepository

interface CrewRepository : JpaRepository<Crew, Long>, CrewCustomRepository {
//    fun findAllByName(name: String): List<Team>
}