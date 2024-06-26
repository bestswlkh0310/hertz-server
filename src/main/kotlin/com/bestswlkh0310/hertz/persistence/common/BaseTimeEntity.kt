package com.bestswlkh0310.hertz.persistence.common

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity(
    @Column(nullable = false)
    @CreatedDate
    open val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    @LastModifiedDate
    open val updatedAt: LocalDateTime = LocalDateTime.now(),
)