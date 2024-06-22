package com.bestswlkh0310.hertz.domain.playlist.api.controller

import com.bestswlkh0310.hertz.domain.playlist.api.dto.request.AddMusicRequest
import com.bestswlkh0310.hertz.domain.playlist.api.dto.request.CreatePlaylistRequest
import com.bestswlkh0310.hertz.domain.playlist.api.dto.request.DeleteMusicPlaylistRequest
import com.bestswlkh0310.hertz.domain.playlist.core.service.PlaylistService
import com.bestswlkh0310.hertz.global.common.Api
import com.bestswlkh0310.hertz.global.response.BaseResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Api.Playlist.PATH)
class PlaylistController(
    private val playlistService: PlaylistService
) {

    @GetMapping(Api.Playlist.PLAYLISTS)
    fun playlists(
        @RequestHeader("Authorization") authorization: String
    ): ResponseEntity<Any> {
        val playlists = playlistService.getPlaylistAll(accessToken = authorization.split(' ')[1])
        return BaseResponse.ok(playlists)
    }

    @PostMapping(Api.Playlist.CREATE_PLAYLIST)
    fun createPlaylist(
        @RequestBody request: CreatePlaylistRequest,
        @RequestHeader("Authorization") authorization: String
    ): ResponseEntity<Any> {
        playlistService.createPlaylist(request, authorization.split(' ')[1])
        return BaseResponse.ok<Unit>()
    }

    @PostMapping(Api.Playlist.ADD_MUSIC)
    fun addMusic(
        @RequestBody request: AddMusicRequest,
        @RequestHeader("Authorization") authorization: String
    ): ResponseEntity<Any> {
        playlistService.addMusic(request, authorization.split(' ')[1])
        return BaseResponse.ok<Unit>()
    }

    @DeleteMapping(Api.Playlist.REMOVE_MUSIC_PLAYLIST)
    fun removeMusicPlaylist(
        @RequestBody request: DeleteMusicPlaylistRequest,
        @RequestHeader("Authorization") authorization: String
    ): ResponseEntity<Any> {
        playlistService.deleteMusic(request, authorization)
        return BaseResponse.ok<Unit>()
    }

}