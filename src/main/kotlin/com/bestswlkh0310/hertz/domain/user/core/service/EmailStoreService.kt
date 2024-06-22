package com.bestswlkh0310.hertz.domain.user.core.service

import com.bestswlkh0310.hertz.domain.user.core.store.EmailCode
import com.bestswlkh0310.hertz.domain.user.core.store.EmailCodeStore
import org.springframework.stereotype.Service

@Service
class EmailStoreService {

    fun isExist(emailCode: EmailCode): Boolean {
        return EmailCodeStore.emailCodes.contains(emailCode)
    }

    fun saveEmailCode(emailCode: EmailCode) {
        EmailCodeStore.emailCodes.removeIf {
            it == emailCode
        }
        EmailCodeStore.emailCodes.add(emailCode)
    }

}