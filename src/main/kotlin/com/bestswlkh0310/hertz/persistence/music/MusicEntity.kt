package com.bestswlkh0310.hertz.persistence.music

import com.bestswlkh0310.hertz.core.music.domain.Music
import com.bestswlkh0310.hertz.core.user.domain.User
import com.bestswlkh0310.hertz.persistence.user.UserEntity
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity(name = "music")
@EntityListeners(AuditingEntityListener::class)
class MusicEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Int,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val description: String,

    @Column(nullable = false)
    val url: String,

    @CreatedDate
    @Column(nullable = false)
    val createdAt: LocalDateTime,

    @ManyToOne(targetEntity = UserEntity::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity
) {
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