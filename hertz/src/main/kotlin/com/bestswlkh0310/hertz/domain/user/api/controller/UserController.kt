package com.bestswlkh0310.hertz.domain.user.api.controller

import com.bestswlkh0310.hertz.domain.user.api.dto.request.EmailCodeRequest
import com.bestswlkh0310.hertz.domain.user.core.service.UserService
import com.bestswlkh0310.hertz.domain.user.api.dto.request.SignInRequest
import com.bestswlkh0310.hertz.domain.user.api.dto.request.SignUpRequest
import com.bestswlkh0310.hertz.global.response.BaseResponse
import com.bestswlkh0310.hertz.global.common.Api
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Api.User.PATH)
class UserController(
    private val userService: UserService,
) {

    @PostMapping(Api.User.SIGN_UP)
    fun signUp(
        @RequestBody signUpRequest: SignUpRequest
    ): ResponseEntity<Any> {
        val response = userService.registerUser(signUpRequest)
        return BaseResponse.ok(response)
    }

    @PostMapping(Api.User.SIGN_IN)
    fun signIn(
        @RequestBody signInRequest: SignInRequest
    ): ResponseEntity<Any> {
        val response = userService.signIn(signInRequest)
        return BaseResponse.ok(response)
    }

    @PostMapping(Api.User.REFRESH)
    fun refresh(
        @RequestHeader("refresh-token") refreshToken: String
    ): ResponseEntity<Any> {
        val response = userService.refresh(refreshToken)
        return BaseResponse.ok(response)
    }

    @PostMapping(Api.User.SEND_EMAIL_CODE)
    fun sendEmailCode(
        @RequestBody emailCodeRequest: EmailCodeRequest
    ): ResponseEntity<Any> {
        userService.sendEmailCode(emailCodeRequest.to)
        return BaseResponse.ok<Unit>()
    }
}