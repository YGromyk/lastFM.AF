package com.gromyk.lastfmaf.presentation.topalbums

import androidx.lifecycle.MutableLiveData
import com.gromyk.api.Api
import com.gromyk.lastfmaf.presentation.base.BaseViewModel
import com.gromyk.lastfmaf.presentation.pojos.AlbumUI
import kotlinx.coroutines.launch
import org.koin.core.inject
import com.gromyk.lastfmaf.presentation.pojos.modelUI

class TopAlbumsViewModel : BaseViewModel() {
    private val api: Api by inject()

    var topAlbums = MutableLiveData<List<AlbumUI>>()
    var areTopAlbumsLoaded = MutableLiveData<Boolean>()

    var searchedArtist: String? = null

    fun fetchArtistInfo(artist: String) = scope.launch {
        api.artistService.getArtistInfo(artist)
    }

    fun searchArtist(name: String) = scope.launch {
        api.artistService.searchArtistByName(name)
    }


    fun fetchTopAlbumsBy(artist: String) = scope.launch {
        searchedArtist = artist
        areTopAlbumsLoaded.postValue(false)
        val albumResponse = api.artistService.getTopAlbumsFor(artist)
        topAlbums.postValue(albumResponse.topAlbums.albums.map {it.modelUI()})
        areTopAlbumsLoaded.postValue(true)
    }
}