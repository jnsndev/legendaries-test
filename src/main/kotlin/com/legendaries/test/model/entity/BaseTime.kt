package com.legendaries.test.model.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseTime {

    @CreationTimestamp
    @Column(updatable = false)
    var createdAt: LocalDateTime? = null

    @UpdateTimestamp
    @Column(insertable = false)
    var modifiedAt: LocalDateTime? = null

    @PrePersist
    fun prePersist() {
        this.createdAt = LocalDateTime.now(ZoneOffset.UTC);
    }

    @PreUpdate
    fun preUpdate() {
        this.modifiedAt = LocalDateTime.now(ZoneOffset.UTC);
    }
}