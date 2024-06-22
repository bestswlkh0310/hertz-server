package com.bestswlkh0310.hertz.domain.music.core.service

import com.bestswlkh0310.hertz.domain.like.core.repository.LikeRepository
import com.bestswlkh0310.hertz.domain.music.api.dto.response.MusicResponse
import com.bestswlkh0310.hertz.domain.music.core.repository.MusicRepository
import com.bestswlkh0310.hertz.domain.user.core.repository.UserRepository
import com.bestswlkh0310.hertz.global.common.ResourcePath
import com.bestswlkh0310.hertz.global.exception.CustomException
import com.bestswlkh0310.hertz.global.exception.ErrorCode
import com.bestswlkh0310.hertz.global.jwt.JwtTokenUtil
import com.bestswlkh0310.hertz.global.jwt.JwtType
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class MusicService(
    private val musicRepository: MusicRepository,
    private val likeRepository: LikeRepository,
    private val userRepository: UserRepository,
    private val jwtTokenUtil: JwtTokenUtil
) {

    fun getMusics(accessToken: String): List<MusicResponse> {
        val username = jwtTokenUtil.getUsername(accessToken.split(' ')[1], type = JwtType.ACCESS_TOKEN)
        val user = userRepository.findByUsername(username)?: throw CustomException(ErrorCode.USER_NOT_FOUND)
        return musicRepository.findAll().map { music ->
            val isLiked = likeRepository.existsByMusicAndUser(music, user)
            MusicResponse.fromEntity(music, isLiked)
        }
    }

    fun getMusicPath(musicId: Int): Resource {
        val musicEntity = musicRepository.findByIdOrNull(musicId)?: throw CustomException(ErrorCode.MUSIC_NOT_FOUND)
        return ClassPathResource("${ResourcePath.MUSIC}/${musicEntity.music}")
    }
}