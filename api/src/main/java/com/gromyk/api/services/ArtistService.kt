package com.gromyk.api.services

import com.gromyk.api.dtos.album.AlbumResponse
import com.gromyk.api.dtos.artist.ArtistInfo
import com.gromyk.api.dtos.topalbums.TopAlbumsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ArtistService {
    @GET("?method=artist.getinfo")
    suspend fun getArtistInfo(@Query("artist") artist: String): Response<ArtistInfo>

    @GET("?method=album.getinfo")
    suspend fun getAlbumInfo(
            @Query("artist") artist: String,
            @Query("album") album: String
    ): Response<AlbumResponse>

    @GET("?method=artist.gettopalbums")
    suspend fun getTopAlbumsFor(@Query("artist") artist: String): Response<TopAlbumsResponse>
}