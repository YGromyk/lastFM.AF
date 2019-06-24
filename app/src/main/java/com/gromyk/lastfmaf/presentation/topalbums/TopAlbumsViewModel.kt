package com.gromyk.lastfmaf.presentation.topalbums

import androidx.lifecycle.MutableLiveData
import com.gromyk.api.Api
import com.gromyk.api.dtos.artist.Artist
import com.gromyk.api.dtos.topalbums.TopAlbum
import com.gromyk.lastfmaf.domain.repository.AlbumRepository
import com.gromyk.lastfmaf.helpers.*
import com.gromyk.lastfmaf.presentation.base.BaseViewModel
import com.gromyk.lastfmaf.presentation.pojos.AlbumUI
import com.gromyk.persistence.composedalbum.AlbumObject
import com.gromyk.persistence.wiki.Wiki
import kotlinx.coroutines.launch
import org.koin.core.inject

class TopAlbumsViewModel : BaseViewModel() {
    private val api: Api by inject()
    private val repository: AlbumRepository by inject()

    val topAlbums = MutableLiveData<List<AlbumUI>>()
    val artistInfo = MutableLiveData<Artist>()
    val savedAlbums = mutableListOf<TopAlbum>()


    val isResultReceived = MutableLiveData<Boolean>()

    private lateinit var savedArtist: Artist
    var searchedArtist: String? = null

    var loadLocalData: Boolean = true

    private fun fetchArtistInfo() = scope.launch {
        val artist = api.artistService.getArtistInfo(searchedArtist!!)
        savedArtist = artist.artist
        artistInfo.postValue(savedArtist)
        repository.saveArtist(savedArtist.toDBArtist())
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
        val albumResponse = api.artistService.getTopAlbumsFor(searchedArtist!!)
        savedAlbums.addAll(albumResponse.topAlbums.albums)
        val loadedAlbums = savedAlbums.map { it.toAlbumUI() }
        decorateLoadedAlbums(loadedAlbums)
        topAlbums.postValue(loadedAlbums)
    }

    private fun fetchLocalData() {
        topAlbums.postValue(getLocalAlbums())
    }

    private fun getLocalAlbums() = repository.getLocalAlbums()
        .map {
            it.toAlbumUI(
                repository.getArtistById(it.album.artistId)?.name ?: ""
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


    fun saveAlbumAndArtist(name: String?, artist: String?) {
        scope.launch {
            val albumDetails = repository.api.artistService.getAlbumInfo(artist!!, name!!)
            val artistId = repository.saveArtist(savedArtist.toDBArtist())
            albumDetails.album.let { album ->
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
    }

    fun removeAlbum(name: String?, artist: String?) {
        scope.launch {
            repository.removeAlbumBy(artist!!, name!!)
            fetchLocalData()
        }
    }
}