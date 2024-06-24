package com.bestswlkh0310.hertz.core.music.service

import com.bestswlkh0310.hertz.core.music.domain.Music
import com.bestswlkh0310.hertz.core.music.req.SaveMusicReq

interface MusicService {
    fun search(query: String): List<Music>
    fun save(req: SaveMusicReq): Music
    fun remove(musicId: Int)
}