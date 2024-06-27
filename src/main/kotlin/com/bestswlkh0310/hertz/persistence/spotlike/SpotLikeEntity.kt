package com.bestswlkh0310.hertz.persistence.spotlike

import com.bestswlkh0310.hertz.core.spotlike.domain.SpotLike
import com.bestswlkh0310.hertz.persistence.common.BaseIdAndTimeEntity
import com.bestswlkh0310.hertz.persistence.common.TableName
import com.bestswlkh0310.hertz.persistence.music.MusicEntity
import com.bestswlkh0310.hertz.persistence.user.UserEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity(name = TableName.SPOT_LIKE)
class SpotLikeEntity(

    override val id: Int,

    @ManyToOne(targetEntity = UserEntity::class, cascade = [CascadeType.REMOVE])
    @JoinColumn(referencedColumnName = "id", name = "user_id")
    val user: UserEntity,

    @ManyToOne(targetEntity = MusicEntity::class, cascade = [CascadeType.REMOVE])
    @JoinColumn(referencedColumnName = "id", name = "music_id")
    val music: MusicEntity,

    override val createdAt: LocalDateTime

) : BaseIdAndTimeEntity(id) {
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