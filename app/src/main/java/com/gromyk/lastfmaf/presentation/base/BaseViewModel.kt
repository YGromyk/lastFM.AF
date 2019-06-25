package com.gromyk.lastfmaf.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gromyk.lastfmaf.presentation.networkstate.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import org.koin.core.KoinComponent
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), KoinComponent {
    val networkState = MutableLiveData<NetworkState>()
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO
    protected val scope = CoroutineScope(coroutineContext)

    private fun cancelAllRequests() = coroutineContext.cancel()

    override fun onCleared() {
        cancelAllRequests()
        super.onCleared()
    }
}