package com.bestswlkh0310.hertz.domain.music.core.model

import com.bestswlkh0310.hertz.domain.musicplaylist.core.model.MusicPlaylistEntity
import com.bestswlkh0310.hertz.domain.playlist.core.model.PlaylistEntity
import jakarta.persistence.*

@Entity
class MusicEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val music: String,
    val author: String,
    @OneToMany(mappedBy = "music")
    val musicPlaylists: List<MusicPlaylistEntity> = arrayListOf()
)
