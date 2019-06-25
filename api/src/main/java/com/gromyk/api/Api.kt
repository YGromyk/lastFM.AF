package com.gromyk.api

import com.gromyk.api.services.ArtistService
import com.gromyk.api.services.SearchService
import org.koin.core.KoinComponent
import org.koin.core.inject

class Api : KoinComponent {
    val artistService: ArtistService by inject()
    val searchService: SearchService by inject()
}