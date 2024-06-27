package com.bestswlkh0310.hertz.core.auth.req

import com.fasterxml.jackson.annotation.JsonCreator
import jakarta.validation.constraints.NotNull

data class ReissueReq @JsonCreator constructor(
    @NotNull
    val refreshToken: String
)
