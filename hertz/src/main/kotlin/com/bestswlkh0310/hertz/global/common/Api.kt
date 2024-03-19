package com.bestswlkh0310.hertz.global.common

sealed interface Api {

    companion object {
        private const val v1 = "/api/v1"
    }

    data object User : Api {
        const val PATH = "${v1}/user"

        const val SIGN_IN = "/sign-in"
        const val SIGN_UP = "/sign-up"
        const val REFRESH = "/refresh"
    }

    data object Music: Api {
        const val PATH = "${v1}/music"

        const val ALL = ""
        const val MUSIC = ""
    }

    data object Health: Api {
        const val PATH = "${v1}/health"

        const val HEALTH = ""
    }
}