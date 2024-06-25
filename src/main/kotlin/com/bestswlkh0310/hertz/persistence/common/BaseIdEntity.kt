package com.bestswlkh0310.hertz.persistence.common

import jakarta.persistence.*

@MappedSuperclass
abstract class BaseIdEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = false)
    open val id: Int
) : BaseTimeEntity()