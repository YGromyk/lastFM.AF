package com.gromyk.lastfmaf.presentation.navigation

import android.os.Bundle

interface Navigator {
    fun navigateTo(@Screen screen: Int, parameters: Bundle? = null)

    companion object {
        val localALbums = "localAlbums"
        val localDetails = "localDetails"

        val search = "search"
        val loadedALbums = "localAlbums"
        val loadedDetails = "localDetails"
    }
}