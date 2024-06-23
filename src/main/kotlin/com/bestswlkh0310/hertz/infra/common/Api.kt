package com.bestswlkh0310.hertz.infra.common

sealed interface Api {

    companion object {
        private const val V1 = "/api/v1"
    }

    data object User : Api {
        const val PATH = "$V1/users"

        const val SIGN_IN = "/sign-in"
        const val SIGN_UP = "/sign-up"
        const val REFRESH = "/refresh"
        const val SEND_EMAIL_CODE = "/send-email-code"
    }
}