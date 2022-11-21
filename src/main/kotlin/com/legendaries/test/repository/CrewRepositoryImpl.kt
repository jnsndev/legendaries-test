package com.legendaries.test.repository

import com.legendaries.test.model.dto.request.crew.CrewFilterCondition
import com.legendaries.test.model.entity.Crew
import com.legendaries.test.model.entity.QCrew.crew
import com.legendaries.test.model.entity.QTeam.team
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import java.time.YearMonth

@Repository
class CrewRepositoryImpl(
    val queryFactory: JPAQueryFactory
) : QuerydslRepositorySupport(Crew::class.java), CrewCustomRepository {

    override fun findAllByCrewFilterCondition(
        crewFilterCondition: CrewFilterCondition,
        pageable: Pageable
    ): Page<Crew> {

        val query = queryFactory
            .selectFrom(crew).distinct()
            .leftJoin(crew.team, team)
            .fetchJoin()
            .where(
                betweenBirthDate(crewFilterCondition.fromBirth, crewFilterCondition.toBirth),
                likeTeamNameOrCrewName(crewFilterCondition.name)
            )

        return PageImpl(
            querydsl!!.applyPagination(pageable, query).fetch(),
            pageable,
            query.fetchCount()
        )
    }

    fun betweenBirthDate(fromBirth: YearMonth?, toBirth: YearMonth?): BooleanExpression? {
        if (fromBirth != null && toBirth != null) {
            val start = fromBirth.atDay(1)
            val end = toBirth.atEndOfMonth()
            return crew.birthDate.between(start, end)
        }

        return null
    }

    fun likeTeamNameOrCrewName(name: String?): BooleanExpression? {
        if (name.isNullOrEmpty()) {
            return null
        }

        return team.name.contains(name).or(crew.name.contains(name))
    }
}