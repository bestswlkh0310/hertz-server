package com.bestswlkh0310.hertz

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class HertzApplication

fun main(args: Array<String>) {
    runApplication<HertzApplication>(*args)
}