package com.bestswlkh0310.hertz.domain.playlist.api.controller

import com.bestswlkh0310.hertz.global.common.Api
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Api.Playlist.PATH)
class PlaylistController {

    @GetMapping(Api.Playlist.PLAYLISTS)
    fun playlists() {

    }

}