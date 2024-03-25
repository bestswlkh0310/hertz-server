package com.bestswlkh0310.hertz.domain.like.core.model

import com.bestswlkh0310.hertz.domain.music.core.model.MusicEntity
import com.bestswlkh0310.hertz.domain.user.core.model.UserEntity
import jakarta.persistence.*

@Entity
class LikeEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "music_id")
    val music: MusicEntity,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id")
    val user: UserEntity
)