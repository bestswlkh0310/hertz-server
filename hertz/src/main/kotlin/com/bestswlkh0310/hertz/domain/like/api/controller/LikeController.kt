package com.bestswlkh0310.hertz.domain.like.api.controller

import com.bestswlkh0310.hertz.domain.like.core.service.LikeService
import com.bestswlkh0310.hertz.global.base.BaseResponse
import com.bestswlkh0310.hertz.global.common.Api
import com.bestswlkh0310.hertz.global.jwt.JwtTokenUtil
import com.bestswlkh0310.hertz.global.jwt.JwtType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(Api.Like.PATH)
class LikeController(
    private val likeService: LikeService,
    private val jwtTokenUtil: JwtTokenUtil
) {

    @PatchMapping("${Api.Like.EDIT}/{musicId}")
    fun editLike(
        @PathVariable("musicId") musicId: Int,
        @RequestHeader("Authorization") accessToken: String
    ): ResponseEntity<out Any> {
        val username = jwtTokenUtil.getUsername(accessToken.split(' ')[1], JwtType.ACCESS_TOKEN)
        val response = likeService.editLike(musicId, username)
        return BaseResponse.ok(response)
    }
}