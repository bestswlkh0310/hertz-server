package com.bestswlkh0310.hertz.persistence.playlist

import com.bestswlkh0310.hertz.core.playlist.domain.Playlist
import com.bestswlkh0310.hertz.persistence.common.TableName
import com.bestswlkh0310.hertz.persistence.music.MusicEntity
import com.bestswlkh0310.hertz.persistence.user.UserEntity
import jakarta.persistence.*

@Entity(name = TableName.PLAYLIST)
class PlaylistEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = true)
    val thumbnailUrl: String?,

    @OneToMany(targetEntity = MusicEntity::class, fetch = FetchType.LAZY)
    val musics: List<MusicEntity> = arrayListOf(),

    @ManyToOne(targetEntity = UserEntity::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity
) {
    fun toDomain() = Playlist(
        id = id,
        title = title,
        thumbnailUrl = thumbnailUrl,
        musics = musics.map { it.toDomain() },
        user = user.toDomain()
    )

    companion object {
        fun of(playlist: Playlist) = PlaylistEntity(
            id = playlist.id,
            title = playlist.title,
            thumbnailUrl = playlist.thumbnailUrl,
            user = UserEntity.of(playlist.user)
        )
    }
}