package com.bestswlkh0310.hertz.core.music.port

import com.bestswlkh0310.hertz.core.music.domain.Music

interface MusicPort {
    fun get(id: Int): Music?

    /**
     * id: User id
     */
    fun getAll(id: Int): List<Music>
    fun save(music: Music): Music

    /**
     * id: Music Id
     */
    fun remove(id: Int)

    /**
     * id: Music id
     */
    fun exists(id: Int): Boolean
}