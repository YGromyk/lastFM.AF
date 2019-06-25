package com.gromyk.lastfmaf.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.gromyk.lastfmaf.presentation.navigation.Navigator
import com.gromyk.lastfmaf.presentation.networkstate.NetworkState
import com.gromyk.lastfmaf.presentation.views.PlaceholderType
import com.gromyk.lastfmaf.presentation.views.PlaceholderView

abstract class BaseFragment : Fragment() {
    protected lateinit var navigator: Navigator
    protected var placeholder: PlaceholderView? = null
    protected abstract val viewModel: BaseViewModel

    fun showError(title: String, message: String) {
        AlertDialog.Builder(context!!)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok") { _, _ -> }
                .create()
                .show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        placeholder = PlaceholderView(view)
        viewModel.networkState.observe(this, Observer(::onNetworkStateChanged))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Navigator) // sorry :(
            navigator = context
    }

    private fun onNetworkStateChanged(networkState: NetworkState) {
        networkState.error?.let {
            Snackbar.make(view!!, it.message ?: "Error", Snackbar.LENGTH_SHORT).show()
            placeholder?.showPlaceholder(0, PlaceholderType.NO_INTERNET)
        }
    }

    protected fun getNavController() = view?.let { Navigation.findNavController(it) }
}