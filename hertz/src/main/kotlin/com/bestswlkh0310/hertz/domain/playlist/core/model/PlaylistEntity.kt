package com.bestswlkh0310.hertz.domain.playlist.core.model

import com.bestswlkh0310.hertz.domain.music.core.model.MusicEntity
import com.bestswlkh0310.hertz.domain.musicplaylist.core.model.MusicPlaylistEntity
import jakarta.persistence.*

@Entity
class PlaylistEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val title: String,
    val author: String,
    val image: String,
    @OneToMany(mappedBy = "playlist")
    val musicPlaylists: List<MusicPlaylistEntity>
)