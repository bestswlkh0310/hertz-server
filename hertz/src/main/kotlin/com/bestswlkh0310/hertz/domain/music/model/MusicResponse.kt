package com.bestswlkh0310.hertz.domain.music.model

import com.bestswlkh0310.hertz.common.Resource
import org.springframework.core.io.ClassPathResource

data class MusicResponse(
    val id: Int,
    val music: String,
    val author: String,
) {

    fun toPathResponse(): ClassPathResource {
        return ClassPathResource("${Resource.music}/${music}")
    }

    companion object {
        fun fromEntity(musicEntity: MusicEntity): MusicResponse
                = MusicResponse(
            id = musicEntity.id,
            music = musicEntity.music,
            author = musicEntity.author
        )
    }
}