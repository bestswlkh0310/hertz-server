package com.bestswlkh0310.hertz.core.playlist.req

data class PlaylistReq(
    val title: String,
    val thumbnailUrl: String,
    val musicIds: List<Int>,
)
