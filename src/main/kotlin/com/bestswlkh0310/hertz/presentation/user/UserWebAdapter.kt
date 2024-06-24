package com.bestswlkh0310.hertz.presentation.user

import com.bestswlkh0310.hertz.core.user.service.UserService
import com.bestswlkh0310.hertz.infra.common.Api
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Api.User.PATH)
class UserWebAdapter(
    private val userService: UserService
) {
    @GetMapping(Api.User.GET)
    fun get(
        @PathVariable("id") id: Int
    ) = userService.get(id)
        .let { ResponseEntity.ok(it) }

    @DeleteMapping(Api.User.LEAVE)
    fun leave() = userService.leave()
        .let { ResponseEntity.ok(it) }
}