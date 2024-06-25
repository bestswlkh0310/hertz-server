package com.bestswlkh0310.hertz.persistence.spotlike

import com.bestswlkh0310.hertz.core.spotlike.domain.SpotLike
import com.bestswlkh0310.hertz.persistence.common.BaseIdEntity
import com.bestswlkh0310.hertz.persistence.common.TableName
import com.bestswlkh0310.hertz.persistence.music.MusicEntity
import com.bestswlkh0310.hertz.persistence.user.UserEntity
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity(name = TableName.SPOT_LIKE)
@EntityListeners(AuditingEntityListener::class)
class SpotLikeEntity(

    override val id: Int,

    @ManyToOne(targetEntity = UserEntity::class)
    @JoinColumn(referencedColumnName = "id", name = "user_id")
    val user: UserEntity,

    @ManyToOne(targetEntity = MusicEntity::class)
    @JoinColumn(referencedColumnName = "id", name = "music_id")
    val music: MusicEntity,

    override val createdAt: LocalDateTime

) : BaseIdEntity(id) {
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