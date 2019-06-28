package com.gromyk.lastfmaf.presentation.albumdetails

import androidx.lifecycle.MutableLiveData
import com.gromyk.api.OnResponse
import com.gromyk.api.dtos.album.AlbumResponse
import com.gromyk.lastfmaf.domain.repository.DataRepository
import com.gromyk.lastfmaf.helpers.isNotBlankAndEmpty
import com.gromyk.lastfmaf.presentation.base.BaseViewModel
import com.gromyk.lastfmaf.presentation.networkstate.onError
import com.gromyk.lastfmaf.presentation.pojos.toAlbumObject
import com.gromyk.persistence.composedalbum.AlbumObject
import kotlinx.coroutines.launch
import org.koin.core.inject

class AlbumDetailsViewModel : BaseViewModel() {
    private val repository: DataRepository by inject()

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
            repository.getAlbumInfo(artist!!, album!!, object : OnResponse<AlbumResponse> {
                override fun onSuccess(responseBody: AlbumResponse) {
                    albumData.postValue(responseBody.album.toAlbumObject())
                }

                override fun onError(exception: Exception) {
                    networkState.onError(Throwable("Network error"))
                }
            })
        }
    }

    private fun fetchLocalAlbum() = scope.launch {
        if (artist.isNotBlankAndEmpty() && album.isNotBlankAndEmpty()) {
            albumData.postValue(repository.getSavedAlbumBy(artist!!, album!!))
        }
    }
}