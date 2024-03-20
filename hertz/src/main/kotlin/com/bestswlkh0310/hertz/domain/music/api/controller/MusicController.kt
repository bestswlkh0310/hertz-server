package com.bestswlkh0310.hertz.domain.music.api.controller

import com.bestswlkh0310.hertz.domain.music.core.service.MusicService
import com.bestswlkh0310.hertz.global.base.BaseResponse
import com.bestswlkh0310.hertz.global.common.Api
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Api.Music.PATH)
class MusicController(
    private val musicService: MusicService
) {

    @GetMapping(Api.Music.ALL)
    fun musics(): ResponseEntity<Any> {
        val musics = musicService.getMusics()
        return BaseResponse.ok(musics)
    }

    @GetMapping("${Api.Music.MUSIC}/{id}")
    fun music(
        @PathVariable("id") id: Int
    ): ResponseEntity<Any> {
        val music = musicService.getMusic(id).toPathResponse()
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=${music.file.name}")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .contentLength(music.file.length())
            .body(music.inputStream.readBytes())
    }
}