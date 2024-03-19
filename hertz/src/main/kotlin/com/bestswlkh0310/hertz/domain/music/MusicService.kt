package com.bestswlkh0310.hertz.domain.music

import com.bestswlkh0310.hertz.domain.music.model.MusicResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class MusicService(
    private val musicRepository: MusicRepository
) {

    fun getMusics(): List<MusicResponse> {
        return musicRepository.findAll().map { MusicResponse.fromEntity(it) }
    }

    fun getMusic(id: Int): MusicResponse {
        val musicEntity = musicRepository.findByIdOrNull(id)

        musicEntity?.let {
            return MusicResponse.fromEntity(musicEntity)
        }
        throw Exception("없다 이년아")
    }

}