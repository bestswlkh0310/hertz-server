package com.bestswlkh0310.hertz.core.auth.service

import com.bestswlkh0310.hertz.core.auth.req.ReissueReq
import com.bestswlkh0310.hertz.core.auth.req.SignInReq
import com.bestswlkh0310.hertz.core.auth.req.SignUpReq
import com.bestswlkh0310.hertz.core.auth.res.TokenRes

interface AuthService {
    fun signIn(req: SignInReq): TokenRes
    fun signUp(req: SignUpReq): TokenRes
    fun reissue(req: ReissueReq): TokenRes
}