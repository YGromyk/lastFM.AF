package com.gromyk.lastfmaf.presentation.base

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.gromyk.lastfmaf.presentation.navigation.Navigator

abstract class BaseFragment : Fragment() {
    protected lateinit var navigator: Navigator

    fun showError(title: String, message: String) {
        AlertDialog.Builder(context!!)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok") { _, _ -> }
                .create()
                .show()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Navigator) // sorry :(
            navigator = context
    }
}