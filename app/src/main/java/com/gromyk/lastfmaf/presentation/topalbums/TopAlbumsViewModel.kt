package com.gromyk.lastfmaf.presentation.topalbums

import androidx.lifecycle.MutableLiveData
import com.gromyk.api.Api
import com.gromyk.api.dtos.artist.Artist
import com.gromyk.lastfmaf.presentation.base.BaseViewModel
import com.gromyk.lastfmaf.presentation.pojos.AlbumUI
import com.gromyk.lastfmaf.presentation.pojos.modelUI
import kotlinx.coroutines.launch
import org.koin.core.inject

class TopAlbumsViewModel : BaseViewModel() {
    private val api: Api by inject()

    val topAlbums = MutableLiveData<List<AlbumUI>>()
    val artistInfo = MutableLiveData<Artist>()

    val isResultReceived = MutableLiveData<Boolean>()

    var searchedArtist: String? = null

    fun fetchArtistInfo() = scope.launch {
        val artist = api.artistService.getArtistInfo(searchedArtist!!)
        artistInfo.postValue(artist.artist)
    }

    fun fetchTopAlbumsBy() = scope.launch {
        isResultReceived.postValue(false)
        val albumResponse = api.artistService.getTopAlbumsFor(searchedArtist!!)
        topAlbums.postValue(albumResponse.topAlbums.albums.map {it.modelUI()})
        isResultReceived.postValue(true)
    }
}