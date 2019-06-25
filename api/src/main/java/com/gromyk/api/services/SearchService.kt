package com.gromyk.api.services

import com.gromyk.api.dtos.search.SearchArtistResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("?method=artist.search")
    suspend fun searchArtist(@Query("artist") artist: String): SearchArtistResponse?
}