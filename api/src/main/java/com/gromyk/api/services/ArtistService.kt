package com.gromyk.api.services

import com.gromyk.api.dtos.artist.ArtistInfo
import com.gromyk.api.dtos.ArtistSearchResponse
import com.gromyk.api.dtos.album.AlbumResponse
import com.gromyk.api.dtos.topalbums.TopAlbumsResponse
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

    @GET("?method=album.getinfo")
    suspend fun getAlbumInfo(
        @Query("artist") artist: String,
        @Query("album") album: String
    ): AlbumResponse

    @GET("?method=artist.gettopalbums")
    suspend fun getTopAlbumsFor(@Query("artist") artist: String): TopAlbumsResponse
}