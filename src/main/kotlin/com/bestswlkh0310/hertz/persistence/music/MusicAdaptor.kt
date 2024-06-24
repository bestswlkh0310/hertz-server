package com.bestswlkh0310.hertz.persistence.music

import com.bestswlkh0310.hertz.core.music.domain.Music
import com.bestswlkh0310.hertz.core.music.port.MusicPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class MusicAdaptor(
    private val musicRepository: MusicRepository
) : MusicPort {
    override fun get(id: Int): Music? =
        musicRepository.findByIdOrNull(id)?.toDomain()

    override fun getAll(id: Int): List<Music> =
        musicRepository.findByUserId(id).map { it.toDomain() }

    override fun getAll(id: List<Int>): List<Music> =
        musicRepository.findByIdIn(id).map { it.toDomain() }

    override fun save(music: Music): Music =
        musicRepository.save(MusicEntity.of(music)).toDomain()

    override fun remove(id: Int) =
        musicRepository.deleteById(id)

    override fun exists(id: Int) =
        musicRepository.existsById(id)
}