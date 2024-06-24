package com.bestswlkh0310.hertz.persistence.spotlike

import com.bestswlkh0310.hertz.core.spotlike.domain.SpotLike
import com.bestswlkh0310.hertz.persistence.music.MusicEntity
import com.bestswlkh0310.hertz.persistence.user.UserEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity(name = "spot_like")
@EntityListeners(AuditingEntityListener::class)
class SpotLikeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @ManyToOne(targetEntity = UserEntity::class)
    @JoinColumn(referencedColumnName = "id", name = "user_id")
    val user: UserEntity,

    @ManyToOne(targetEntity = MusicEntity::class)
    @JoinColumn(referencedColumnName = "id", name = "music_id")
    val music: MusicEntity,

    @CreatedDate
    @Column(nullable = false)
    val createdAt: LocalDateTime
) {
    fun toDomain() = SpotLike(
        id = id,
        user = user.toDomain(),
        music = music.toDomain(),
        createdAt = createdAt
    )

    companion object {
        fun of(spotLike: SpotLike) = SpotLikeEntity(
            id = spotLike.id,
            user = UserEntity.of(spotLike.user),
            music = MusicEntity.of(spotLike.music),
            createdAt = spotLike.createdAt
        )
    }
}