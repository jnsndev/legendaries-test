package com.legendaries.test.model.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.FetchType.*

@Entity
@Table(name = "crew")
class Crew(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id")
    val team: Team,

    val name: String,

    val birthDate: LocalDate,

    val phone: String,

    val joiningDate: LocalDateTime,

    @CreatedDate
    val createdAt: LocalDateTime,

    @LastModifiedDate
    val modifiedAt: LocalDateTime
) {
}