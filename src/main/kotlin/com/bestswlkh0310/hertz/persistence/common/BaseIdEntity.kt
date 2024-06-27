package com.bestswlkh0310.hertz.persistence.common

import jakarta.persistence.*

@MappedSuperclass
abstract class BaseIdEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Int = 0
)