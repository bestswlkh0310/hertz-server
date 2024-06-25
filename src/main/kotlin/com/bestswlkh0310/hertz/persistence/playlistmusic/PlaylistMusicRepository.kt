package com.bestswlkh0310.hertz.persistence.playlistmusic

import org.springframework.data.jpa.repository.JpaRepository

interface PlaylistMusicRepository : JpaRepository<PlaylistMusicEntity, Int> {
}