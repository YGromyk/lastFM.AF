package com.gromyk.api

import com.gromyk.api.dtos.artist.ArtistInfo
import com.gromyk.api.dtos.ArtistSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistService {
    @GET("?method=artist.getinfo")
    suspend fun getArtistInfo(@Query("artist") artist: String): ArtistInfo

    @GET("?method=artist.search")
    suspend fun searchArtistByName(
        @Query("artist") artist: String,
        @Query("limit") limit: Int? = null,
        @Query("page") page: Int? = null
    ): ArtistSearchResponse
}