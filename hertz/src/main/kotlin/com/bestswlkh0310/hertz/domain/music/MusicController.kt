package com.bestswlkh0310.hertz.domain.music

import com.bestswlkh0310.hertz.common.Api
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("${Api.v1}/musics")
class MusicController(
    private val musicService: MusicService
) {

    @GetMapping(value = ["/", ""])
    fun musics(): ResponseEntity<Any> {
        val musics = musicService.getMusics()
        return ResponseEntity.ok(musics)
    }

    @GetMapping("/{id}")
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