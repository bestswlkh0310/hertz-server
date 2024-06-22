package com.bestswlkh0310.hertz.domain.playlist.api.dto.response

import com.bestswlkh0310.hertz.domain.playlist.core.model.PlaylistEntity
import com.bestswlkh0310.hertz.domain.user.api.dto.response.UserResponse

data class PlaylistResponse(
    var id: Int,
    var title: String,
    var image: String?,
    var user: UserResponse
) {
    companion object {
        fun formEntity(playlistEntity: PlaylistEntity): PlaylistResponse
        = PlaylistResponse(
            id = playlistEntity.id?: -1,
            title = playlistEntity.title,
            image = playlistEntity.image,
            user = UserResponse.fromEntity(playlistEntity.user)
        )
    }
}