package com.gromyk.lastfmaf.presentation.albumdetails

import androidx.lifecycle.MutableLiveData
import com.gromyk.api.Api
import com.gromyk.lastfmaf.domain.repository.AlbumRepository
import com.gromyk.lastfmaf.helpers.isNotBlankAndEmpty
import com.gromyk.lastfmaf.presentation.pojos.toAlbumObject
import com.gromyk.lastfmaf.presentation.base.BaseViewModel
import com.gromyk.lastfmaf.presentation.networkstate.onError
import com.gromyk.persistence.composedalbum.AlbumObject
import kotlinx.coroutines.launch
import org.koin.core.inject

class AlbumDetailsViewModel : BaseViewModel() {
    private val repository: AlbumRepository by inject()

    val albumData = MutableLiveData<AlbumObject>()
    val isAlbumLoaded = MutableLiveData<Boolean>()

    var artist: String? = null
    var album: String? = null
    var loadLocalData = true

    fun fetchData() {
        isAlbumLoaded.postValue(false)
        if (loadLocalData) {
            fetchLocalAlbum()
        } else {
            fetchAlbumsInfo()
        }
        isAlbumLoaded.postValue(true)
    }

    private fun fetchAlbumsInfo() = scope.launch {
        if (!showErrorIsNoNetwork()) return@launch
        if (artist.isNotBlankAndEmpty() && album.isNotBlankAndEmpty()) {
            val albumResponse = repository.getAlbumInfo(artist!!, album!!)
            if (albumResponse.isSuccessful) {
                albumData.postValue(albumResponse.body()?.album?.toAlbumObject())
            } else {
                networkState.onError(Throwable("Network error"))
            }
        }
    }

    private fun fetchLocalAlbum() = scope.launch {
        if (artist.isNotBlankAndEmpty() && album.isNotBlankAndEmpty()) {
            albumData.postValue(repository.getSavedAlbumBy(artist!!, album!!))
        }
    }
}