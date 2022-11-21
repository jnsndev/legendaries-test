package com.legendaries.test.repository

import com.legendaries.test.model.dto.request.team.TeamFilterCondition
import com.legendaries.test.model.entity.QTeam.team
import com.legendaries.test.model.entity.Team
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class TeamRepositoryImpl(
    val queryFactory: JPAQueryFactory
) : QuerydslRepositorySupport(Team::class.java), TeamCustomRepository {

    override fun findAllByTeamFilterCondition(
        teamFilterCondition: TeamFilterCondition,
        pageable: Pageable
    ): Page<Team> {

        val query = queryFactory
            .selectFrom(team).distinct()
            .where(likeTeamName(teamFilterCondition.name))

        return PageImpl(
            querydsl!!.applyPagination(pageable, query).fetch(),
            pageable,
            query.fetchCount()
        )
    }

    fun likeTeamName(name: String?): BooleanExpression? {
        if (name.isNullOrEmpty()) {
            return null
        }

        return team.name.contains(name)
    }
}