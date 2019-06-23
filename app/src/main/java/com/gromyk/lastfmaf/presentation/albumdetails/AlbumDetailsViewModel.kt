package com.gromyk.lastfmaf.presentation.albumdetails

import androidx.lifecycle.MutableLiveData
import com.gromyk.api.Api
import com.gromyk.api.dtos.album.Album
import com.gromyk.lastfmaf.helpers.isNotBlankAndEmpty
import com.gromyk.lastfmaf.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.inject

class AlbumDetailsViewModel : BaseViewModel() {
    private val api: Api by inject()
    val albumData = MutableLiveData<Album>()
    val isAlbumLoaded = MutableLiveData<Boolean>()

    var artist: String? = null
    var album: String? = null

    fun fetchAlbumsInfo() = scope.launch {
        if (artist.isNotBlankAndEmpty() && album.isNotBlankAndEmpty()) {
            isAlbumLoaded.postValue(false)
            val albumResponse = api.artistService.getAlbumInfo(artist!!, album!!)
            albumData.postValue(albumResponse.album)
            isAlbumLoaded.postValue(true)
        }
    }
}