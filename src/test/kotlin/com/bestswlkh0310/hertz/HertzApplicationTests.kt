package com.bestswlkh0310.hertz

import com.bestswlkh0310.hertz.domain.email.api.dto.EmailMessage
import com.bestswlkh0310.hertz.domain.email.core.service.EmailService
import com.bestswlkh0310.hertz.domain.music.core.model.MusicEntity
import com.bestswlkh0310.hertz.domain.music.core.repository.MusicRepository
import com.bestswlkh0310.hertz.domain.music.core.service.MusicService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource

@SpringBootTest
class HertzApplicationTests {

	@Autowired
	lateinit var emailService: EmailService

	@Autowired
	lateinit var musicRepository: MusicRepository

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

	@Test
	fun generateEmailCode() {
		println(EmailCodeUtil.generateEmailCode(6))
		println(EmailCodeUtil.generateEmailCode(8))
		println(EmailCodeUtil.generateEmailCode(10))
		println(EmailCodeUtil.generateEmailCode(100))
	}

	@Test
	fun addMusic() {
		val e = MusicEntity(
			music = "백예린의 _예뻤어_ AI cover Ver.  DAY6 - _You Were Beautiful_ Yerin Baek AI cover ver. [lyrics].mp3",
			author = "ai",
		)
		for (i in 0..<10) {
			musicRepository.save(e)
		}
	}
}
