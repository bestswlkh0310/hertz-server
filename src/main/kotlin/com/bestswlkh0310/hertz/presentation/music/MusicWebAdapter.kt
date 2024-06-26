package com.bestswlkh0310.hertz.presentation.music

import com.bestswlkh0310.hertz.core.music.req.EditMusicReq
import com.bestswlkh0310.hertz.core.music.req.SaveMusicReq
import com.bestswlkh0310.hertz.core.music.service.MusicService
import com.bestswlkh0310.hertz.presentation.common.Api
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
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
        .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @DeleteMapping(Api.Music.REMOVE)
    fun remove(
        @PathVariable("musicId") musicId: Int
    ) = musicService.remove(musicId)
        .let { ResponseEntity.ok(it) }

    @GetMapping(Api.Music.GET_ALL)
    fun getAll(
        @RequestParam("userId") userId: Int
    ) = musicService.getAll(userId)
        .let { ResponseEntity.ok(it) }

    @PatchMapping(Api.Music.EDIT)
    fun edit(
        @RequestBody req: EditMusicReq
    ) = musicService.editMusic(req)
        .let { ResponseEntity.ok(it) }
}