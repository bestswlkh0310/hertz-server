package com.bestswlkh0310.hertz

import com.bestswlkh0310.hertz.domain.email.api.dto.EmailMessage
import com.bestswlkh0310.hertz.domain.email.core.service.EmailService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource

@SpringBootTest
class HertzApplicationTests {

	@Autowired
	lateinit var emailService: EmailService

	@Test
	fun musics() {
		val musics = ClassPathResource("music").file.listFiles()
		musics.forEach {
			println(it.name)
		}
	}

	@Test
	fun sendEmail() {
		val emailMessage = EmailMessage(
			to = "hhhello0507@gmail.com",
			subject = "[헤르츠] 이메일 인증 코드",
			message = "123456"
		)
		emailService.sendMessage(emailMessage)
	}
}
