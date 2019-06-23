package com.gromyk.lastfmaf.presentation.search

import androidx.lifecycle.MutableLiveData
import com.gromyk.api.Api
import com.gromyk.api.dtos.artist.Artist
import com.gromyk.lastfmaf.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.inject

class SearchViewModel : BaseViewModel() {
    private val api: Api by inject()

    val artistsData = MutableLiveData<List<Artist>>()
    val isResultReceived = MutableLiveData<Boolean>()

    fun searchArtist(name: String) {
        scope.launch {
            isResultReceived.postValue(false)
            val artists = api.searchService.searchArtist(name)
            artistsData.postValue(artists?.results?.artistMatches?.artist ?: emptyList())
            isResultReceived.postValue(true)
        }
    }
}