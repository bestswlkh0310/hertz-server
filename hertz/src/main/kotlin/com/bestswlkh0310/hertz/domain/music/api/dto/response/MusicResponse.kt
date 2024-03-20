package com.bestswlkh0310.hertz.domain.music.api.dto.response

import com.bestswlkh0310.hertz.domain.music.core.model.MusicEntity
import com.bestswlkh0310.hertz.global.common.ResourcePath
import org.springframework.core.io.ClassPathResource

data class MusicResponse(
    val id: Int,
    val music: String,
    val author: String,
    val isLiked: Boolean
) {


    companion object {
        fun fromEntity(musicEntity: MusicEntity, isLiked: Boolean): MusicResponse = MusicResponse(
            id = musicEntity.id,
            music = musicEntity.music,
            author = musicEntity.author,
            isLiked = isLiked
        )
    }
}