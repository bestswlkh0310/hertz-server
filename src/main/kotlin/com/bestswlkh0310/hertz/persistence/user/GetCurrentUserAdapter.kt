package com.bestswlkh0310.hertz.persistence.user

import com.bestswlkh0310.hertz.core.user.domain.User
import com.bestswlkh0310.hertz.core.user.port.GetCurrentUserPort
import com.bestswlkh0310.hertz.infra.security.CustomUserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class GetCurrentUserAdapter : GetCurrentUserPort {
    override fun get() = userDetails.user
    override fun getId() = userDetails.user.id
    private val userDetails: CustomUserDetails
        get() {
            return (SecurityContextHolder.getContext().authentication.principal as CustomUserDetails)
        }
}