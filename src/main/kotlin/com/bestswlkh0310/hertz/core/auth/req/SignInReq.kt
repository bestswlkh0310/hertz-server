package com.bestswlkh0310.hertz.core.auth.req

import jakarta.validation.constraints.NotNull

data class SignInReq(
    @NotNull
    val email: String,

    @NotNull
    val password: String
)
