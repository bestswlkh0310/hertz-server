package com.bestswlkh0310.hertz.domain.music.model

import com.bestswlkh0310.hertz.common.Resource
import org.springframework.core.io.ClassPathResource

data class Music(
    var id: Int,
    var music: String,
    var author: String
) {

    fun toResponse(): MusicResponse
    = MusicResponse(
        id,
        music,
        author
    )

    fun toPathResponse(): ClassPathResource {
        return ClassPathResource("${Resource.music}/${music}")
    }

    companion object {
        fun fromEntity(musicEntity: MusicEntity): Music
        = Music(
            id = musicEntity.id,
            music = musicEntity.music,
            author = musicEntity.author
        )
    }
}
