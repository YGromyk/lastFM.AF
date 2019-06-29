package com.gromyk.lastfmaf.presentation.albums

import androidx.lifecycle.MutableLiveData
import com.gromyk.api.OnResponse
import com.gromyk.api.dtos.album.AlbumResponse
import com.gromyk.api.dtos.artist.Artist
import com.gromyk.api.dtos.artist.ArtistInfo
import com.gromyk.api.dtos.topalbums.TopAlbum
import com.gromyk.api.dtos.topalbums.TopAlbums
import com.gromyk.lastfmaf.domain.repository.DataRepository
import com.gromyk.lastfmaf.helpers.clearAndInsert
import com.gromyk.lastfmaf.presentation.base.BaseViewModel
import com.gromyk.lastfmaf.presentation.networkstate.onError
import com.gromyk.lastfmaf.presentation.pojos.*
import com.gromyk.persistence.composedalbum.AlbumObject
import com.gromyk.persistence.wiki.Wiki
import kotlinx.coroutines.launch
import org.koin.core.inject

class AlbumsViewModel : BaseViewModel() {
    private val repository: DataRepository by inject()

    val topAlbums = MutableLiveData<List<AlbumUI>>()
    val artistInfo = MutableLiveData<Artist>()
    private val savedAlbums = mutableListOf<TopAlbum>()


    val isResultReceived = MutableLiveData<Boolean>()

    private lateinit var savedArtist: Artist
    var searchedArtist: String? = null

    var loadLocalData: Boolean = true

    private fun fetchArtistInfo() = scope.launch {
        if (!showErrorIsNoNetwork()) return@launch
        searchedArtist?.let {
            repository.getArtistInfo(it, onResponse = object : OnResponse<ArtistInfo> {
                override fun onSuccess(responseBody: ArtistInfo) {
                    savedArtist = responseBody.artist
                    artistInfo.postValue(savedArtist)
                    repository.saveArtist(savedArtist.toDBArtist())
                }

                override fun onError(exception: Exception) {
                    networkState.onError(Throwable("Network error"))
                }
            })
        }
    }

    fun fetchData() {
        scope.launch {
            isResultReceived.postValue(false)
            if (loadLocalData) {
                fetchLocalData()
            } else {
                fetchTopAlbums()
                fetchArtistInfo()
            }
            isResultReceived.postValue(true)
        }
    }

    private fun fetchTopAlbums() = scope.launch {
        if (!showErrorIsNoNetwork()) return@launch
        repository.getTopAlbumsFor(searchedArtist!!, object : OnResponse<TopAlbums> {
            override fun onSuccess(responseBody: TopAlbums) {
                savedAlbums.clearAndInsert(responseBody.albums)
                val loadedAlbums = savedAlbums.map { it.toAlbumUI() }
                decorateLoadedAlbums(loadedAlbums)
                this@AlbumsViewModel.topAlbums.postValue(loadedAlbums)
            }

            override fun onError(exception: Exception) {
                networkState.onError(Throwable("Network error"))
            }
        })
    }

    private fun fetchLocalData() {
        topAlbums.postValue(getLocalAlbums())
    }

    private fun getLocalAlbums() = repository.getLocalAlbums()
        .toMutableList()
        .map { albumObject ->
            albumObject.toAlbumUI(
                albumObject.album?.artistId?.let {
                    repository.getArtistById(it)?.name
                } ?: ""
            )
        }

    private fun decorateLoadedAlbums(loadedAlbums: List<AlbumUI>) {
        val localAlbums = getLocalAlbums()
        loadedAlbums.forEach { loadedAlbum ->
            loadedAlbum.isSaved = localAlbums.find {
                it.artist == loadedAlbum.artist && it.name == loadedAlbum.name
            } != null
        }
    }


    fun saveAlbumAndArtist(album: String?, artist: String?) {
        scope.launch {
            if (!showErrorIsNoNetwork()) return@launch
            repository.getAlbumInfo(artist!!, album!!, object : OnResponse<AlbumResponse> {
                override fun onSuccess(responseBody: AlbumResponse) {
                    responseBody.album.let { album ->
                        val artistId = repository.saveArtist(savedArtist.toDBArtist())
                        val albumObject = AlbumObject(
                            album.toDBAlbum().apply { this.artistId = artistId },
                            album.wiki?.toDBWiki() ?: Wiki(),
                            album.image.map { it.toDBImage() },
                            album.tracks.track?.map { it.toDBTrack() } ?: emptyList(),
                            album.tags.tag?.map { it.toDBTag() } ?: emptyList()
                        )
                        repository.saveAlbum(albumObject)
                    }
                }

                override fun onError(exception: Exception) {
                    networkState.onError(Throwable("Network error"))
                }
            })
        }
    }

    fun removeAlbum(name: String?, artist: String?) {
        scope.launch {
            repository.removeAlbumBy(artist!!, name!!)
            if (loadLocalData)
                fetchLocalData()
        }
    }
}