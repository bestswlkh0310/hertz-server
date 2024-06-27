package com.bestswlkh0310.hertz.persistence.music

import com.bestswlkh0310.hertz.core.music.domain.Music
import com.bestswlkh0310.hertz.persistence.common.BaseIdAndTimeEntity
import com.bestswlkh0310.hertz.persistence.common.TableName
import com.bestswlkh0310.hertz.persistence.user.UserEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = TableName.MUSIC)
class MusicEntity(
    override val id: Int,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val description: String,

    @Column(nullable = false)
    val url: String,

    @ManyToOne(targetEntity = UserEntity::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity,

    override val createdAt: LocalDateTime
) : BaseIdAndTimeEntity() {

    fun toDomain() = Music(
        id = id,
        name = name,
        description = description,
        url = url,
        createdAt = createdAt,
        user = user.toDomain()
    )

    companion object {
        fun of(music: Music) = MusicEntity(
            id = music.id,
            name = music.name,
            description = music.description,
            url = music.url,
            createdAt = music.createdAt,
            user = UserEntity.of(music.user)
        )
    }
}