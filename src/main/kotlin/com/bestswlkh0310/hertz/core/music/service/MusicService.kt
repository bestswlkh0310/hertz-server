package com.bestswlkh0310.hertz.core.music.service

import com.bestswlkh0310.hertz.core.music.req.SaveMusicReq
import com.bestswlkh0310.hertz.core.music.res.MusicRes

interface MusicService {
    fun search(query: String): List<MusicRes>
    fun save(req: SaveMusicReq): MusicRes
    fun remove(musicId: Int): String

    /**
     * id: User id
     */
    fun getAll(id: Int): List<MusicRes>
}