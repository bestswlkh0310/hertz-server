package com.bestswlkh0310.hertz.domain.musicplaylist.core.model

import com.bestswlkh0310.hertz.domain.music.core.model.MusicEntity
import com.bestswlkh0310.hertz.domain.playlist.core.model.PlaylistEntity
import jakarta.persistence.*

@Entity
class MusicPlaylistEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "music_id")
    val music: MusicEntity,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "playlist_id")
    val playlist: PlaylistEntity
)