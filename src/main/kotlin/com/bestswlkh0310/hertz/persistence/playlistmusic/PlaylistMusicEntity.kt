package com.bestswlkh0310.hertz.persistence.playlistmusic

import com.bestswlkh0310.hertz.core.playlistmusic.domain.PlaylistMusic
import com.bestswlkh0310.hertz.persistence.common.BaseIdAndTimeEntity
import com.bestswlkh0310.hertz.persistence.common.TableName
import com.bestswlkh0310.hertz.persistence.music.MusicEntity
import com.bestswlkh0310.hertz.persistence.playlist.PlaylistEntity
import jakarta.persistence.*

@Entity
@Table(name = TableName.PLAYLIST_MUSIC)
class PlaylistMusicEntity(
    override val id: Int,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "music_id", nullable = false)
    val music: MusicEntity,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "playlist_id", nullable = false)
    val playlist: PlaylistEntity
) : BaseIdAndTimeEntity() {
    fun toDomain() = PlaylistMusic(
        id = id,
        music = music.toDomain(),
        playlist = playlist.toDomain()
    )
    companion object {
        fun of(playlistMusic: PlaylistMusic) = PlaylistMusicEntity(
            id = playlistMusic.id,
            music = MusicEntity.of(playlistMusic.music),
            playlist = PlaylistEntity.of(playlistMusic.playlist)
        )
    }
}