package com.bestswlkh0310.hertz.domain.playlist.core.service

import com.bestswlkh0310.hertz.domain.music.core.repository.MusicRepository
import com.bestswlkh0310.hertz.domain.playlist.api.dto.request.AddMusicRequest
import com.bestswlkh0310.hertz.domain.playlist.api.dto.request.CreatePlaylistRequest
import com.bestswlkh0310.hertz.domain.playlist.api.dto.request.DeleteMusicPlaylistRequest
import com.bestswlkh0310.hertz.domain.musicplaylist.core.model.MusicPlaylistEntity
import com.bestswlkh0310.hertz.domain.musicplaylist.core.repository.MusicPlaylistRepository
import com.bestswlkh0310.hertz.domain.playlist.api.dto.response.PlaylistResponse
import com.bestswlkh0310.hertz.domain.playlist.core.model.PlaylistEntity
import com.bestswlkh0310.hertz.domain.playlist.core.repository.PlaylistRepository
import com.bestswlkh0310.hertz.domain.user.core.repository.UserRepository
import com.bestswlkh0310.hertz.global.exception.CustomException
import com.bestswlkh0310.hertz.global.exception.ErrorCode
import com.bestswlkh0310.hertz.global.jwt.JwtTokenUtil
import com.bestswlkh0310.hertz.global.jwt.JwtType
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PlaylistService(
    private val playlistRepository: PlaylistRepository,
    private val musicRepository: MusicRepository,
    private val musicPlaylistRepository: MusicPlaylistRepository,
    private val userRepository: UserRepository,
    private val jwtTokenUtil: JwtTokenUtil
) {

    fun getPlaylistAll(accessToken: String): List<PlaylistResponse> {
        val username = jwtTokenUtil.getUsername(accessToken, type = JwtType.ACCESS_TOKEN)
        val user = userRepository.findByUsername(username)?: throw CustomException(ErrorCode.USER_NOT_FOUND)
        val playlists = playlistRepository.findByUser(user).map { PlaylistResponse.formEntity(it) }
        return playlists
    }

    fun createPlaylist(request: CreatePlaylistRequest, accessToken: String) {
        val username = jwtTokenUtil.getUsername(accessToken, type = JwtType.ACCESS_TOKEN)
        val user = userRepository.findByUsername(username)?: throw CustomException(ErrorCode.USER_NOT_FOUND)
        val newPlaylist = PlaylistEntity(
            title = request.title,
            image = request.image,
            user = user
        )
        playlistRepository.save(newPlaylist)
    }

    fun addMusic(request: AddMusicRequest, accessToken: String) {
        val username = jwtTokenUtil.getUsername(accessToken, type = JwtType.ACCESS_TOKEN)
        val user = userRepository.findByUsername(username)?: throw CustomException(ErrorCode.USER_NOT_FOUND)
        val music = musicRepository.findByIdOrNull(request.musicId)?: throw CustomException(ErrorCode.MUSIC_NOT_FOUND)
        val playlist =
            playlistRepository.findByIdOrNull(request.playlistId) ?: throw CustomException(ErrorCode.PLAYLIST_NOT_FOUND)
        val isExist = musicPlaylistRepository.existsByMusicAndPlaylist(music, playlist)
        if (isExist) {
            throw CustomException(ErrorCode.ALREADY_EXIST_MUSIC_PLAYLIST)
        }
        if (playlist.user != user) {
            throw CustomException(ErrorCode.CAN_NOT_ACCESS_PLAYLIST)
        }
        val newMusicPlaylist = MusicPlaylistEntity(
            music = music,
            playlist = playlist
        )
        musicPlaylistRepository.save(newMusicPlaylist)
    }

    fun deleteMusic(request: DeleteMusicPlaylistRequest, accessToken: String) {
        val username = jwtTokenUtil.getUsername(accessToken, type = JwtType.ACCESS_TOKEN)
        val user = userRepository.findByUsername(username)?: throw CustomException(ErrorCode.USER_NOT_FOUND)
        val music = musicRepository.findByIdOrNull(request.musicId)?: throw CustomException(ErrorCode.MUSIC_NOT_FOUND)
        val playlist =
            playlistRepository.findByIdOrNull(request.playlistId) ?: throw CustomException(ErrorCode.PLAYLIST_NOT_FOUND)
        if (playlist.user != user) {
            throw CustomException(ErrorCode.CAN_NOT_ACCESS_PLAYLIST)
        }
        val isExist = musicPlaylistRepository.existsByMusicAndPlaylist(music, playlist)
        if (!isExist) {
            throw CustomException(ErrorCode.ALREADY_EXIST_MUSIC_PLAYLIST)
        }
        val musicPlaylist = musicPlaylistRepository.findByMusicAndPlaylist(music, playlist)
        musicPlaylistRepository.delete(musicPlaylist)
    }

}