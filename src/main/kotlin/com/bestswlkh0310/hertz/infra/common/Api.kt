package com.bestswlkh0310.hertz.infra.common

sealed interface Api {

    companion object {
        private const val V1 = "/api/v1"
    }

    data object Auth : Api {
        const val PATH = "$V1/auth"

        const val SIGN_IN = "/sign-in"
        const val SIGN_UP = "/sign-up"
        const val REISSUE = "/reissue"
    }

    data object User : Api {
        const val PATH = "$V1/user"

        const val GET = "/{id}"
    }

    data object Music: Api {
        const val PATH = "$V1/music"

        const val GET_ALL = "/all"
        const val SEARCH = "/search"
        const val SAVE = "/save"
        const val REMOVE = "/remove/{musicId}"
        const val EDIT = "/edit"
    }

    data object SpotLike : Api {
        const val PATH = "$V1/spot-like"

        const val SET = "/set/{musicId}"
    }
}