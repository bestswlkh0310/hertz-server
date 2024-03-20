package com.bestswlkh0310.hertz.domain.like.api.controller

import com.bestswlkh0310.hertz.domain.like.core.service.LikeService
import com.bestswlkh0310.hertz.global.response.BaseResponse
import com.bestswlkh0310.hertz.global.common.Api
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
) {

    @PatchMapping("${Api.Like.EDIT}/{musicId}")
    fun editLike(
        @PathVariable("musicId") musicId: Int,
        @RequestHeader("Authorization") accessToken: String
    ): ResponseEntity<Any> {
        val response = likeService.editLike(musicId, accessToken)
        return BaseResponse.ok(response)
    }
}