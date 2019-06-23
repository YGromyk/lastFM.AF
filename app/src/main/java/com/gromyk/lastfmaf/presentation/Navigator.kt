package com.gromyk.lastfmaf.presentation

interface Navigator {
    fun openWebPage(url: String)

    companion object Routes {
        const val ALBUM_DETAILS = "albumsDetails"
        const val OPEN_WEB = "openWeb"
    }
}