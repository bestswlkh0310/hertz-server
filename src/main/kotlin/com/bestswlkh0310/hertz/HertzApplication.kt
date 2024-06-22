package com.bestswlkh0310.hertz

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*


@SpringBootApplication
class HertzApplication {

	@PostConstruct
	fun started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
	}

}

fun main(args: Array<String>) {
	runApplication<HertzApplication>(*args)
}