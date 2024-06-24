package com.bestswlkh0310.hertz.core.user.service

import com.bestswlkh0310.hertz.core.user.port.GetCurrentUserPort
import com.bestswlkh0310.hertz.core.user.port.UserPort
import com.bestswlkh0310.hertz.core.user.res.UserRes
import com.bestswlkh0310.hertz.infra.exception.CustomException
import com.bestswlkh0310.hertz.infra.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userPort: UserPort,
    private val getCurrentUserPort: GetCurrentUserPort
) : UserService {
    override fun get(id: Int): UserRes {
        val user = userPort.get(id) ?: throw CustomException(ErrorCode.NOT_FOUND)
        return UserRes.of(user)
    }

    override fun leave(): String {
        val user = getCurrentUserPort.get() ?: throw CustomException(ErrorCode.NOT_FOUND)
        userPort.remove(user.id)
        return "success"
    }
}