package com.legendaries.test.repository

import com.legendaries.test.model.dto.request.TeamFilterCondition
import com.legendaries.test.model.entity.QCrew.crew
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
            .leftJoin(team.crewList, crew)
            .where(likeTeamName(teamFilterCondition.name))

        return PageImpl(
            querydsl!!.applyPagination(pageable, query).fetch(),
            pageable,
            query.fetchCount()
        )
    }

//    override fun findByTeamId(teamId: Long): Team {
//
//        return queryFactory
//            .select(team).distinct()
//            .from(team)
//            .leftJoin(team.crewList, crew)
//            .where(team.id.eq(teamId))
//            .fetchFirst()
//
//    }


    fun likeTeamName(teamName: String?): BooleanExpression? {
        if (teamName.isNullOrEmpty()) {
            return null
        }

        return team.name.contains(teamName)
    }
}