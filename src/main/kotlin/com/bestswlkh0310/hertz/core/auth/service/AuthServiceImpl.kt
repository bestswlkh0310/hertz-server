package com.bestswlkh0310.hertz.core.auth.service

import com.bestswlkh0310.hertz.core.auth.domain.JwtType
import com.bestswlkh0310.hertz.core.auth.port.TokenPort
import com.bestswlkh0310.hertz.core.auth.req.ReissueReq
import com.bestswlkh0310.hertz.core.auth.req.SignInReq
import com.bestswlkh0310.hertz.core.auth.req.SignUpReq
import com.bestswlkh0310.hertz.core.auth.res.TokenRes
import com.bestswlkh0310.hertz.core.user.domain.User
import com.bestswlkh0310.hertz.core.user.domain.UserRole
import com.bestswlkh0310.hertz.core.user.port.UserPort
import com.bestswlkh0310.hertz.infra.exception.CustomException
import com.bestswlkh0310.hertz.infra.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val userPort: UserPort,
    private val tokenPort: TokenPort
) : AuthService {
    override fun signIn(req: SignInReq): TokenRes {
        val email = req.email
        val password = req.password

        if (!userPort.exists(email, password)) {
            throw CustomException(ErrorCode.NOT_FOUND)
        }

        val token = tokenPort.createToken(email)
        return TokenRes.of(token)
    }

    override fun signUp(req: SignUpReq): TokenRes {
        val email = req.email
        val password = req.password

        if (userPort.exists(email, password)) {
            throw CustomException(ErrorCode.ALREADY_EXISTS)
        }

        val user = User(email = email, password = password, role = UserRole.MEMBER)
        userPort.insert(user)
        val token = tokenPort.createToken(email)
        return TokenRes.of(token)
    }

    override fun reissue(req: ReissueReq): TokenRes {
        val isExpired = tokenPort.isExpired(req.refreshToken, type = JwtType.REFRESH_TOKEN)
        if (isExpired) {
            throw CustomException(ErrorCode.INVALID_AUTH_TOKEN)
        }

        val newToken =  tokenPort.reissue(req.refreshToken)
        return TokenRes.of(newToken)
    }
}