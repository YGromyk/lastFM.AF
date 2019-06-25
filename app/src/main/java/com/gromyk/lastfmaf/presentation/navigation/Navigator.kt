package com.gromyk.lastfmaf.presentation.navigation

import android.os.Bundle

interface Navigator {
    fun navigateTo(@Screen screen: Int, parameters: Bundle? = null)
}