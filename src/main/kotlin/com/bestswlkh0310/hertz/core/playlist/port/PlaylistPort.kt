package com.bestswlkh0310.hertz.core.playlist.port

import com.bestswlkh0310.hertz.core.playlist.domain.Playlist

interface PlaylistPort {

    fun get(id: Int): Playlist?
    /**
     * id: User id
     */
    fun getAll(id: Int): List<Playlist>
    fun create(playlist: Playlist): Playlist
    fun edit(playlist: Playlist)
    fun remove(id: Int)
}