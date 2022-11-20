package com.legendaries.test.model.entity

import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name = "team")
@DynamicUpdate
class Team(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true, length = 100)
    var name: String,

    var maxCrewCount: Int,

    @OneToMany(mappedBy = "team", cascade = [CascadeType.ALL], orphanRemoval = true)
    var crewList: Set<Crew> = hashSetOf(),
) : BaseTime() {

    fun update(name: String, maxCrewCount: Int) {
        this.name = name
        this.maxCrewCount = maxCrewCount
    }
}