package com.gromyk.lastfmaf.presentation.topalbums

import androidx.lifecycle.MutableLiveData
import com.gromyk.api.Api
import com.gromyk.api.dtos.album.Album
import com.gromyk.api.dtos.artist.Artist
import com.gromyk.lastfmaf.domain.repository.AlbumRepository
import com.gromyk.lastfmaf.helpers.toDBAlbum
import com.gromyk.lastfmaf.helpers.toUIAlbum
import com.gromyk.lastfmaf.presentation.base.BaseViewModel
import com.gromyk.lastfmaf.presentation.pojos.AlbumUI
import com.gromyk.lastfmaf.presentation.pojos.modelUI
import kotlinx.coroutines.launch
import org.koin.core.inject

class TopAlbumsViewModel : BaseViewModel() {
    private val api: Api by inject()
    private val repository: AlbumRepository by inject()

    val topAlbums = MutableLiveData<List<AlbumUI>>()
    val artistInfo = MutableLiveData<Artist>()

    val savedAlbums = mutableListOf<com.gromyk.persistence.album.Album>()

    val isResultReceived = MutableLiveData<Boolean>()

    var searchedArtist: String? = null

    fun fetchArtistInfo() = scope.launch {
        val artist = api.artistService.getArtistInfo(searchedArtist!!)
        artistInfo.postValue(artist.artist)
    }

    fun fetchTopAlbumsBy() = scope.launch {
        isResultReceived.postValue(false)
        val albumResponse = api.artistService.getTopAlbumsFor(searchedArtist!!)
        savedAlbums.addAll(albumResponse.topAlbums.albums.map { it.toDBAlbum() })
        repository.getLocalAlbums()
        topAlbums.postValue(repository.getLocalAlbums().map { it.toUIAlbum() })
        isResultReceived.postValue(true)
    }

    fun saveAlbum(name: String?, artist: String?) {
        scope.launch {
            savedAlbums.find { it.artist == artist && it.name == name }?.let {
                repository.saveAlbum(it)
            }
        }
    }

    fun removeAlbum(name: String?, artist: String?) {
//        val albumToSave = albums?.find { it.artist?.isBlank() }
    }
}