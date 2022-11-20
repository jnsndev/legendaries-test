package com.legendaries.test.repository

import com.legendaries.test.model.entity.Team
import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<Team, Long>, TeamCustomRepository {
    fun findAllByName(name: String): List<Team>
}