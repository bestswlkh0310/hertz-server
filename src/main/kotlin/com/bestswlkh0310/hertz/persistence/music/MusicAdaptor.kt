package com.bestswlkh0310.hertz.persistence.music

import com.bestswlkh0310.hertz.core.music.domain.Music
import com.bestswlkh0310.hertz.core.music.port.MusicPort
import org.springframework.stereotype.Component

@Component
class MusicAdaptor(
    private val musicRepository: MusicRepository
) : MusicPort {
    override fun save(music: Music): Music =
        musicRepository.save(MusicEntity.of(music)).toDomain()

    override fun remove(id: Int) =
        musicRepository.deleteById(id)
}