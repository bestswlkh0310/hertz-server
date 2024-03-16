package com.bestswlkh0310.hertz.domain.music

import com.bestswlkh0310.hertz.domain.music.model.Music
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class MusicService(
    private val musicRepository: MusicRepository
) {

    fun getMusics(): List<Music> {
        return musicRepository.findAll().map { Music.fromEntity(it) }
    }

    fun getMusic(id: Int): Music {
        val musicEntity = musicRepository.findByIdOrNull(id)

        musicEntity?.let {
            return Music.fromEntity(musicEntity)
        }
        throw Exception("없다 이년아")
    }

}