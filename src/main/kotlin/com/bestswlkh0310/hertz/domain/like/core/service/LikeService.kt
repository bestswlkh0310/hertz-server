package com.bestswlkh0310.hertz.domain.like.core.service

import com.bestswlkh0310.hertz.domain.like.core.model.LikeEntity
import com.bestswlkh0310.hertz.domain.like.core.repository.LikeRepository
import com.bestswlkh0310.hertz.domain.music.core.repository.MusicRepository
import com.bestswlkh0310.hertz.domain.user.core.repository.UserRepository
import com.bestswlkh0310.hertz.global.exception.CustomException
import com.bestswlkh0310.hertz.global.exception.ErrorCode
import com.bestswlkh0310.hertz.global.jwt.JwtTokenUtil
import com.bestswlkh0310.hertz.global.jwt.JwtType
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LikeService(
    private val likeRepository: LikeRepository,
    private val userRepository: UserRepository,
    private val musicRepository: MusicRepository,
    private val jwtTokenUtil: JwtTokenUtil
) {
    fun editLike(musicId: Int, accessToken: String) {
        val username = jwtTokenUtil.getUsername(accessToken.split(' ')[1], type = JwtType.ACCESS_TOKEN)
        val user = userRepository.findByUsername(username)?: throw CustomException(ErrorCode.USER_NOT_FOUND)
        val music = musicRepository.findByIdOrNull(musicId)?: throw CustomException(ErrorCode.MUSIC_NOT_FOUND)
        val like = likeRepository.findByMusicAndUser(music, user)

        if (like == null) { // create
            val newLike = LikeEntity(
                music = music,
                user = user
            )
            likeRepository.save(newLike)
        } else { // delete
            likeRepository.delete(like)
        }
    }
}