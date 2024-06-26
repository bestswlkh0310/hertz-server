package com.bestswlkh0310.hertz.core.spotlike

import com.bestswlkh0310.hertz.core.spotlike.domain.SpotLike

interface SpotLikePort {
    fun exists(userId: Int, musicId: Int): Boolean
    fun create(spotLike: SpotLike): SpotLike
    fun remove(userId: Int, musicId: Int)
}