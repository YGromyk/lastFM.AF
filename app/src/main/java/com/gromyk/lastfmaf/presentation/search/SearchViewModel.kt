package com.gromyk.lastfmaf.presentation.search

import androidx.lifecycle.MutableLiveData
import com.gromyk.api.OnResponse
import com.gromyk.api.dtos.artist.Artist
import com.gromyk.api.dtos.search.SearchArtistResponse
import com.gromyk.lastfmaf.domain.repository.DataRepository
import com.gromyk.lastfmaf.presentation.base.BaseViewModel
import com.gromyk.lastfmaf.presentation.networkstate.onError
import kotlinx.coroutines.launch
import org.koin.core.inject

class SearchViewModel : BaseViewModel() {
    private val repository: DataRepository by inject()

    val artistsData = MutableLiveData<List<Artist>>()
    val isResultReceived = MutableLiveData<Boolean>()

    var lastSearched = ""

    fun searchArtist(name: String) {
        if (!showErrorIsNoNetwork()) return
        scope.launch {
            isResultReceived.postValue(false)
            repository.searchArtistBy(name = name, onResponse = object : OnResponse<SearchArtistResponse> {
                override fun onSuccess(responseBody: SearchArtistResponse) {
                    artistsData.postValue(responseBody.results?.artistMatches?.artist ?: emptyList())
                    lastSearched = name
                }

                override fun onError(exception: Exception) {
                    networkState.onError(exception)
                }
            })
            isResultReceived.postValue(true)
        }
    }
}