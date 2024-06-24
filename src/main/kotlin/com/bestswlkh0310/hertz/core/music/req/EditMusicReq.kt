package com.bestswlkh0310.hertz.core.music.req

data class EditMusicReq(
    /**
     * music id
     */
    val id: Int,
    val name: String,
    val description: String
)
