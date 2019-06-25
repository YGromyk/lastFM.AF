package com.gromyk.lastfmaf.presentation.search

import androidx.lifecycle.MutableLiveData
import com.gromyk.api.Api
import com.gromyk.api.dtos.artist.Artist
import com.gromyk.lastfmaf.domain.repository.AlbumRepository
import com.gromyk.lastfmaf.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.inject

class SearchViewModel : BaseViewModel() {
    private val repository: AlbumRepository by inject()

    val artistsData = MutableLiveData<List<Artist>>()
    val isResultReceived = MutableLiveData<Boolean>()

    var lastSearched = ""

    fun searchArtist(name: String) {
        if (!showErrorIsNoNetwork()) return
        scope.launch {
            isResultReceived.postValue(false)
            val artists = repository.searchArtistBy(name = name)
            artistsData.postValue(artists?.results?.artistMatches?.artist ?: emptyList())
            isResultReceived.postValue(true)
            lastSearched = name
        }
    }
}