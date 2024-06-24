package com.bestswlkh0310.hertz.core.playlist.service

import com.bestswlkh0310.hertz.core.playlist.req.PlaylistReq
import com.bestswlkh0310.hertz.core.playlist.res.PlaylistRes

interface PlaylistService {
    /**
     * id: User id
     */
    fun getAll(id: Int): List<PlaylistRes>
    fun create(req: PlaylistReq): PlaylistRes

    /**
     * id: Playlist id
     */
    fun edit(req: PlaylistReq, id: Int): PlaylistRes
    fun remove(id: Int)
}