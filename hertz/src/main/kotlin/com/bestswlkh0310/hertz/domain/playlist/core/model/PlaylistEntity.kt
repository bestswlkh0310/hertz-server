package com.bestswlkh0310.hertz.domain.playlist.core.model

import com.bestswlkh0310.hertz.domain.music.core.model.MusicEntity
import com.bestswlkh0310.hertz.domain.musicplaylist.core.model.MusicPlaylistEntity
import com.bestswlkh0310.hertz.domain.user.core.model.UserEntity
import jakarta.persistence.*

@Entity
class PlaylistEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val title: String,
    val image: String?,
    @OneToMany(mappedBy = "playlist")
    val musicPlaylists: List<MusicPlaylistEntity> = arrayListOf(),
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id")
    val user: UserEntity
)