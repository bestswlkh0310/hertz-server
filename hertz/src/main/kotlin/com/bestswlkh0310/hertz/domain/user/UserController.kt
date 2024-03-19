package com.bestswlkh0310.hertz.domain.user

import com.bestswlkh0310.hertz.domain.user.model.SignInRequest
import com.bestswlkh0310.hertz.domain.user.model.SignUpRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/sign-up")
    fun signUp(
        @RequestBody signUpRequest: SignUpRequest
    ): ResponseEntity<Any> {
        val response = userService.registerUser(signUpRequest)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/sign-in")
    fun signIn(
        @RequestBody signInRequest: SignInRequest
    ): ResponseEntity<Any> {
        val response = userService.signIn(signInRequest)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/refresh")
    fun refresh(
        @RequestHeader("refresh-token") refreshToken: String
    ): ResponseEntity<Any> {
        val response = userService.refresh(refreshToken)
        return ResponseEntity.ok(response)
    }
}