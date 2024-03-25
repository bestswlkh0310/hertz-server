package com.bestswlkh0310.hertz.domain.user.core.model

import com.bestswlkh0310.hertz.domain.playlist.core.model.PlaylistEntity
import com.bestswlkh0310.hertz.domain.user.core.model.consts.UserRole
import jakarta.persistence.*

@Entity
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val username: String,
    val password: String,
    @Enumerated(EnumType.STRING)
    val role: UserRole,
    @OneToMany(mappedBy = "user")
    val playlists: List<PlaylistEntity>
)
