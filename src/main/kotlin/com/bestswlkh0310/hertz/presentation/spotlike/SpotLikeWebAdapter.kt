package com.bestswlkh0310.hertz.presentation.spotlike

import com.bestswlkh0310.hertz.core.spotlike.service.SpotLikeService
import com.bestswlkh0310.hertz.infra.common.Api
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Api.SpotLike.PATH)
class SpotLikeWebAdapter(
    private val spotLikeService: SpotLikeService
) {

    @PostMapping(Api.SpotLike.SET)
    fun set(
        @PathVariable("musicId") musicId: Int
    ) = spotLikeService.set(musicId = musicId)
        .let { ResponseEntity.ok(it) }

}