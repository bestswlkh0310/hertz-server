package com.bestswlkh0310.hertz.presentation.common

sealed interface Api {

    companion object {
        private const val V1 = "/api/v1"
    }

    data object Health: Api {
        const val PATH = "$V1/health"
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
        const val LEAVE = "/leave"
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

    data object Playlist : Api {
        const val PATH = "$V1/playlist"

        const val GET_ALL = "/all"
        const val CREATE = "/create"
        const val EDIT = "/edit/{playlistId}"
        const val REMOVE = "/remove/{playlistId}"
    }
}