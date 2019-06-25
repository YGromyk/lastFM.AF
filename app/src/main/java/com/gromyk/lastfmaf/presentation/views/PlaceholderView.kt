package com.gromyk.lastfmaf.presentation.views

import android.view.View
import com.gromyk.lastfmaf.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_content.*
import kotlinx.android.synthetic.main.recycler_placeholder.*

class PlaceholderView(override val containerView: View?) : LayoutContainer {
    fun showPlaceholder(itemsCount: Int, @PlaceholderType type: Int, description: String = "") {
        var imageResource: Int? = null
        var cause = ""
        if (itemsCount > 0) {
            placeholder?.visibility = View.GONE
            swipeRefreshLayout?.visibility = View.VISIBLE
        } else {
            placeholder?.visibility = View.VISIBLE
            when (type) {
                PlaceholderType.NO_INTERNET -> {
                    imageResource = R.drawable.ic_closed
                    cause = "Check your internet connection"
                }
                PlaceholderType.NO_ARTISTS -> {
                    imageResource = R.drawable.ic_no_music
                    cause = "Cannot find $description in artists"

                }
                PlaceholderType.NO_ALBUMS -> {
                    imageResource = R.drawable.ic_no_music
                    cause = "You don't have saved albums"
                }
                PlaceholderType.NO_SONGS -> {
                    imageResource = R.drawable.ic_no_music
                    cause = "This album doesn't have songs :("
                }
                PlaceholderType.SEARCH -> {
                    imageResource = R.drawable.ic_search
                    cause = "Start to search"
                }
            }
            imageResource?.let {
                placeholderImage?.setImageResource(it)
            }
            placeholderDescription?.text = cause
            swipeRefreshLayout?.visibility = View.GONE
        }
    }
}