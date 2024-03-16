package com.bestswlkh0310.hertz

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource

@SpringBootTest
class HertzApplicationTests {

	@Test
	fun musics() {
		val musics = ClassPathResource("music").file.listFiles()
		musics.forEach {
			println(it.name)
		}
	}
}
