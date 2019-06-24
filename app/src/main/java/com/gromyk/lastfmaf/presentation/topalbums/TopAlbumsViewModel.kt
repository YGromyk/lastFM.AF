package com.gromyk.lastfmaf.presentation.topalbums

import androidx.lifecycle.MutableLiveData
import com.gromyk.api.Api
import com.gromyk.api.dtos.artist.Artist
import com.gromyk.api.dtos.topalbums.TopAlbum
import com.gromyk.lastfmaf.domain.repository.AlbumRepository
import com.gromyk.lastfmaf.helpers.*
import com.gromyk.lastfmaf.presentation.base.BaseViewModel
import com.gromyk.lastfmaf.presentation.pojos.AlbumUI
import com.gromyk.persistence.AlbumObject
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

    fun fetchArtistInfo() = scope.launch {
        val artist = api.artistService.getArtistInfo(searchedArtist!!)
        savedArtist = artist.artist
        artistInfo.postValue(savedArtist)
    }

    fun fetchTopAlbumsBy() = scope.launch {
        isResultReceived.postValue(false)
        val albumResponse = api.artistService.getTopAlbumsFor(searchedArtist!!)
        savedAlbums.addAll(albumResponse.topAlbums.albums)
        // savedAlbums.map { it.toAlbumUI() }
        topAlbums.postValue(repository.getLocalAlbums().map { it.toAlbumUI() })
        isResultReceived.postValue(true)
    }

    fun saveAlbum(name: String?, artist: String?) {
        scope.launch {
            val albumDetails = repository.api.artistService.getAlbumInfo(artist!!, name!!)
            albumDetails.album.let {
                val albumObject = AlbumObject(
                    it.toDBAlbum(),
                    it.wiki?.toDBWiki(),
                    it.image.map { it.toDBImage() } ?: emptyList(),
                    it.tracks.track?.map { it.toDBTrack() } ?: emptyList(),
                    it.tags.tag?.map { it.toDBTag() } ?: emptyList(),
                    savedArtist.toDBArtist()
                )
                repository.saveAlbum(albumObject)
            }
        }
    }

    fun removeAlbum(name: String?, artist: String?) {
//        val albumToSave = albums?.find { it.artist?.isBlank() }
    }
}