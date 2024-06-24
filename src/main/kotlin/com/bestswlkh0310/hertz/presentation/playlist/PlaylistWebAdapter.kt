package com.bestswlkh0310.hertz.presentation.playlist

import com.bestswlkh0310.hertz.core.playlist.req.PlaylistReq
import com.bestswlkh0310.hertz.core.playlist.service.PlaylistService
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
@RequestMapping(Api.Playlist.PATH)
class PlaylistWebAdapter(
    private val playlistService: PlaylistService
) {

    @GetMapping(Api.Playlist.GET_ALL)
    fun getAll(
        @RequestParam("userId") userId: Int
    ) = playlistService.getAll(userId)
        .let { ResponseEntity.ok(it) }

    @PostMapping(Api.Playlist.CREATE)
    fun create(
        @RequestBody req: PlaylistReq
    ) = playlistService.create(req)
        .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @PatchMapping(Api.Playlist.EDIT)
    fun edit(
        @RequestBody req: PlaylistReq,
        @PathVariable("playlistId") playlistId: Int
    ) = playlistService.edit(req, playlistId)
        .let { ResponseEntity.ok(it) }

    @DeleteMapping(Api.Playlist.REMOVE)
    fun remove(
        @PathVariable("playlistId") playlistId: Int
    ) = playlistService.remove(playlistId)
        .let { ResponseEntity.ok(it) }
}