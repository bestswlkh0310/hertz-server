package com.bestswlkh0310.hertz.domain.email.core.service

import com.bestswlkh0310.hertz.domain.email.api.dto.EmailMessage
import com.bestswlkh0310.hertz.global.exception.CustomException
import com.bestswlkh0310.hertz.global.exception.ErrorCode
import jakarta.mail.MessagingException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.security.core.context.SecurityContextHolder.setContext
import org.springframework.stereotype.Service


@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {
    fun sendMessage(emailMessage: EmailMessage) {
        val mimeMessage = javaMailSender.createMimeMessage()

        try {
            val mimeMessageHelper = MimeMessageHelper(mimeMessage, false, "UTF-8")
            mimeMessageHelper.setTo(emailMessage.to) // 메일 수신자
            mimeMessageHelper.setSubject(emailMessage.subject) // 메일 제목
            mimeMessageHelper.setText(emailMessage.message) // 메일 본문 내용, HTML 여부
            javaMailSender.send(mimeMessage)
        } catch (e: MessagingException) {
            throw CustomException(ErrorCode.SEND_EMAIL_ERROR)
        } catch (e: Exception) {
            throw CustomException(ErrorCode.INTERNAL_SERVER_ERROR)
        }
    }
}