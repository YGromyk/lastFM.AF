package com.gromyk.lastfmaf.presentation.base

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gromyk.lastfmaf.App
import com.gromyk.lastfmaf.presentation.networkstate.NetworkState
import com.gromyk.lastfmaf.presentation.networkstate.onError
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

    protected fun showErrorIsNoNetwork(): Boolean {
        val isNetworkAvailable =isInternetAvailable(App.getApp())
        if (!isNetworkAvailable)
            networkState.onError(Throwable("There is no network"))
        return isNetworkAvailable
    }

    private fun isInternetAvailable(context: Context): Boolean {
        val mConMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return (mConMgr.activeNetworkInfo != null
                && mConMgr.activeNetworkInfo.isConnected)
    }
}