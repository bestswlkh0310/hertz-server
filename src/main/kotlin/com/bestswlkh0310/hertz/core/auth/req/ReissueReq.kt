package com.bestswlkh0310.hertz.core.auth.req

import com.fasterxml.jackson.annotation.JsonCreator

data class ReissueReq @JsonCreator constructor(
    val refreshToken: String
)
