package com.bestswlkh0310.hertz.presentation.music

import com.bestswlkh0310.hertz.core.music.req.SaveMusicReq
import com.bestswlkh0310.hertz.core.music.service.MusicService
import com.bestswlkh0310.hertz.infra.common.Api
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Api.Music.PATH)
class MusicWebAdapter(
    private val musicService: MusicService
) {

    @GetMapping(Api.Music.SEARCH)
    fun search(
        @RequestParam("query") query: String,
    ) = musicService.search(query)
        .let { ResponseEntity.ok(it) }

    @PostMapping(Api.Music.SAVE)
    fun save(
        @RequestBody req: SaveMusicReq
    ) = musicService.save(req)
        .let { ResponseEntity.ok(it) }

    @DeleteMapping(Api.Music.REMOVE)
    fun remove(
        @PathVariable("musicId") musicId: Int
    ) = musicService.remove(musicId)
        .let { ResponseEntity.ok(it) }
}