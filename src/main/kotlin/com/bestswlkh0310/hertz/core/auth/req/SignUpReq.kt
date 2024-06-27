package com.bestswlkh0310.hertz.core.auth.req

import org.jetbrains.annotations.NotNull

data class SignUpReq(
    @NotNull
    val email: String,

    @NotNull
    val password: String
)