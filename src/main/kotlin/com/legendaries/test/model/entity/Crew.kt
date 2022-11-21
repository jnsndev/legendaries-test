package com.legendaries.test.model.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.persistence.*
import javax.persistence.FetchType.*

@Entity
@Table(name = "crew")
//@DynamicUpdate
class Crew(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id")
    var team: Team,

    var name: String,

    var birthDate: LocalDate,

    var phone: String,

    val joiningDate: LocalDate? = LocalDate.now(ZoneOffset.UTC),
) : BaseTime() {

    fun update(team: Team, name: String, birthDate: LocalDate, phone: String) {
        this.name = name
        this.team = team
        this.birthDate = birthDate
        this.phone = phone
    }
}