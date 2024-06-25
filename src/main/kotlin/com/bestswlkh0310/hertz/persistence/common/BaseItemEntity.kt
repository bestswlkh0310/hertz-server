package com.bestswlkh0310.hertz.persistence.common

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseTimeEntity(
    @Column(nullable = false)
    open val createdAt: LocalDateTime = LocalDateTime.now()
)