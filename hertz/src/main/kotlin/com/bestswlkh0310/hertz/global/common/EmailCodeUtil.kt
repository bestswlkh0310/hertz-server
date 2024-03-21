package com.bestswlkh0310.hertz.global.common

import kotlin.random.Random

object EmailCodeUtil {

    const val DEFAULT_LENGTH = 6

    const val DEFAULT_SUBJECT = "[헤르츠] - 이메일 인증 요청"

    fun generateEmailCode(length: Int): String {
        val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9') // 영어 소문자, 대문자, 숫자로 이루어진 문자열 풀
        return (1..length)
            .map { Random.nextInt(0, charPool.size) } // 문자열 풀에서 랜덤으로 인덱스 선택
            .map(charPool::get) // 선택된 인덱스에 해당하는 문자 가져오기
            .joinToString("")
    }
}