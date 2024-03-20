package com.bestswlkh0310.hertz.global.common

sealed interface Api {

    companion object {
        private const val V1 = "/api/v1"
    }

    data object User : Api {
        const val PATH = "${V1}/users"

        const val SIGN_IN = "/sign-in"
        const val SIGN_UP = "/sign-up"
        const val REFRESH = "/refresh"
    }

    data object Music: Api {
        const val PATH = "${V1}/musics"

        const val ALL = ""
        const val MUSIC = ""
    }

    data object Like: Api {
        const val PATH = "${V1}/likes"

        const val EDIT = "/edit"
    }

    data object Health: Api {
        const val PATH = "${V1}/health"

        const val HEALTH = ""
    }
}