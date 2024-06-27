package com.bestswlkh0310.hertz.persistence.playlist

import com.bestswlkh0310.hertz.core.playlist.domain.Playlist
import com.bestswlkh0310.hertz.persistence.common.BaseIdAndTimeEntity
import com.bestswlkh0310.hertz.persistence.common.TableName
import com.bestswlkh0310.hertz.persistence.user.UserEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = TableName.PLAYLIST)
class PlaylistEntity(
    override val id: Int,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = true)
    val thumbnailUrl: String?,

    @ManyToOne(targetEntity = UserEntity::class, fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity,

    override val createdAt: LocalDateTime

    ) : BaseIdAndTimeEntity() {
    fun toDomain() = Playlist(
        id = id,
        title = title,
        thumbnailUrl = thumbnailUrl,
        user = user.toDomain(),
        createdAt = createdAt
    )

    companion object {
        fun of(playlist: Playlist) = PlaylistEntity(
            id = playlist.id,
            title = playlist.title,
            thumbnailUrl = playlist.thumbnailUrl,
            user = UserEntity.of(playlist.user),
            createdAt = playlist.createdAt
        )
    }
}