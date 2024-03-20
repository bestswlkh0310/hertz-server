package com.bestswlkh0310.hertz.domain.music.api.controller

import com.bestswlkh0310.hertz.domain.music.core.service.MusicService
import com.bestswlkh0310.hertz.global.response.BaseResponse
import com.bestswlkh0310.hertz.global.common.Api
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Api.Music.PATH)
class MusicController(
    private val musicService: MusicService
) {

    @GetMapping(Api.Music.ALL)
    fun musics(
        @RequestHeader("Authorization") accessToken: String
    ): ResponseEntity<Any> {
        val musics = musicService.getMusics(accessToken)
        return BaseResponse.ok(musics)
    }

    @GetMapping("${Api.Music.MUSIC}/{id}")
    fun music(
        @PathVariable("id") id: Int,
    ): ResponseEntity<Any> {
        val musicPath = musicService.getMusicPath(id)
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=${musicPath.file.name}")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .contentLength(musicPath.file.length())
            .body(musicPath.inputStream.readBytes())
    }
}