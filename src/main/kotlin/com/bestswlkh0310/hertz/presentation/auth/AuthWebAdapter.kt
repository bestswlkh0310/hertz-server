package com.bestswlkh0310.hertz.presentation.auth

import com.bestswlkh0310.hertz.core.auth.req.ReissueReq
import com.bestswlkh0310.hertz.core.auth.req.SignInReq
import com.bestswlkh0310.hertz.core.auth.req.SignUpReq
import com.bestswlkh0310.hertz.core.auth.service.AuthService
import com.bestswlkh0310.hertz.presentation.common.Api
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Api.Auth.PATH)
class AuthWebAdapter(
    private val authService: AuthService
) {

    @PostMapping(Api.Auth.SIGN_UP)
    fun signUp(
        @RequestBody req: SignUpReq
    ) = authService.signUp(req)
        .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @PostMapping(Api.Auth.SIGN_IN)
    fun signIn(
        @RequestBody req: SignInReq
    ) = authService.signIn(req)
        .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @PostMapping(Api.Auth.REISSUE)
    fun reissue(
        @RequestBody req: ReissueReq
    ) = authService.reissue(req)
        .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }
}