package com.bestswlkh0310.hertz.persistence.user

import com.bestswlkh0310.hertz.core.user.domain.User
import com.bestswlkh0310.hertz.core.user.port.UserPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class UserAdapter(
    private val userRepository: UserRepository
) : UserPort {
    override fun get(id: Int): User? =
        userRepository.findByIdOrNull(id)?.toDomain()

    override fun getByEmail(email: String): User? =
        userRepository.findByEmail(email)?.toDomain()

    override fun insert(user: User): User =
        userRepository.save(UserEntity.of(user)).toDomain()

    override fun exists(email: String, password: String): Boolean =
        userRepository.existsByEmailAndPassword(email, password)
}