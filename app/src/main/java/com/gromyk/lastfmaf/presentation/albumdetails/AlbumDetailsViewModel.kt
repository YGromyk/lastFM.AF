package com.gromyk.lastfmaf.presentation.albumdetails

import androidx.lifecycle.MutableLiveData
import com.gromyk.api.Api
import com.gromyk.lastfmaf.domain.repository.AlbumRepository
import com.gromyk.lastfmaf.helpers.isNotBlankAndEmpty
import com.gromyk.lastfmaf.helpers.toAlbumObject
import com.gromyk.lastfmaf.presentation.base.BaseViewModel
import com.gromyk.persistence.composedalbum.AlbumObject
import kotlinx.coroutines.launch
import org.koin.core.inject

class AlbumDetailsViewModel : BaseViewModel() {
    private val api: Api by inject()
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
        if (artist.isNotBlankAndEmpty() && album.isNotBlankAndEmpty()) {
            val albumResponse = api.artistService.getAlbumInfo(artist!!, album!!)
            albumData.postValue(albumResponse.album.toAlbumObject())
        }
    }

    private fun fetchLocalAlbum() = scope.launch {
        albumData.postValue(repository.getSavedAlbumBy(artist!!, album!!))
    }
}