package com.gromyk.lastfmaf.presentation.navigation

import android.os.Bundle

interface Navigator {
    fun openWebPage(url: String)
    fun navigateTo(@Screen screen: Int, parameters: Bundle? = null)
}