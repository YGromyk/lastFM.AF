package com.gromyk.lastfmaf.presentation.topalbums

import com.gromyk.api.Api
import com.gromyk.api.ApiFactory
import com.gromyk.lastfmaf.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.inject

class TopAlbumsViewModel : BaseViewModel() {
    private val api: Api by inject()
    fun fetchTopAlbums() = scope.launch {
        println(api.artistService.getArtistInfo("Suicide Boys"))
    }

    fun searchArtist(name: String) = scope.launch {
        api.artistService.searchArtistByName(name)
    }
}