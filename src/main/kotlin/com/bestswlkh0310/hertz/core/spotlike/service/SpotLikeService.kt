package com.bestswlkh0310.hertz.core.spotlike.service

import com.bestswlkh0310.hertz.core.spotlike.res.SpotLikeRes

interface SpotLikeService {
    fun set(musicId: Int): SpotLikeRes
}