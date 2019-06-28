package com.gromyk.lastfmaf.domain.repository

import com.gromyk.api.Api
import com.gromyk.api.OnResponse
import com.gromyk.api.dtos.album.AlbumResponse
import com.gromyk.api.dtos.artist.ArtistInfo
import com.gromyk.api.dtos.search.SearchArtistResponse
import com.gromyk.api.dtos.topalbums.TopAlbums
import com.gromyk.lastfmaf.helpers.clearAndInsert
import com.gromyk.persistence.artist.Artist
import com.gromyk.persistence.composedalbum.AlbumObject
import com.gromyk.persistence.db.AppDatabase
import org.koin.core.KoinComponent
import org.koin.core.inject

class DataRepository : KoinComponent {
    private val api: Api by inject()
    private val database: AppDatabase by inject()

    private val localAlbums = mutableListOf<AlbumObject>()

    fun getLocalAlbums(): List<AlbumObject> {
        val albums = database.getAllAlbums().toMutableList()
        localAlbums.clearAndInsert(albums)
        return localAlbums
    }

    fun saveArtist(artist: Artist): Long {
        val artists = database.getAllArtists()
        artists.find { it.url == artist.url }?.let {
            return it.id
        }
        val artistId = artists.map { it.id }.max() ?: 0L
        database.saveArtist(artist.apply {
            id = artistId + 1
        })
        return artist.id
    }

    fun getArtistById(artistId: Long) =
        database.getAllArtists().firstOrNull { it.id == artistId }

    fun saveAlbum(album: AlbumObject) {
        val albumId = localAlbums.mapNotNull { it.album.albumId }.max() ?: 0L
        val isSaved = database.saveAlbum(album.apply {
            this.album.albumId = albumId + 1
        })
        if (isSaved)
            localAlbums.add(album)
    }

    fun removeAlbumBy(artist: String, name: String) {
        getSavedAlbumBy(artist, name)?.let {
            database.removeAlbums(it)
        }
    }

    fun getSavedAlbumBy(artistName: String, albumName: String): AlbumObject? {
        val artist = database.getArtistBy(artistName)
        return localAlbums
            .find {
                it.album.artistId == artist.id && it.album.name == albumName
            }
    }

    suspend fun getAlbumInfo(
        artist: String,
        name: String,
        onResponse: OnResponse<AlbumResponse>
    ) {
        try {
            val response = api.artistService.getAlbumInfo(artist, name)!!
            onResponse.onSuccess(response)
        } catch (exception: Exception) {
            onResponse.onError(exception)
        }
    }

    suspend fun searchArtistBy(
        name: String,
        onResponse: OnResponse<SearchArtistResponse>
    ) {
        try {
            val searchResponse = api.searchService.searchArtist(name)!!
            onResponse.onSuccess(searchResponse)
        } catch (exception: Exception) {
            onResponse.onError(exception)
        }
    }

    suspend fun getArtistInfo(name: String, onResponse: OnResponse<ArtistInfo>) {
        try {
            val artistInfo = api.artistService.getArtistInfo(name)
            onResponse.onSuccess(artistInfo)
        } catch (exception: Exception) {
            onResponse.onError(exception)
        }
    }

    suspend fun getTopAlbumsFor(name: String, onResponse: OnResponse<TopAlbums>) {
        try {
            val artistInfo = api.artistService.getTopAlbumsFor(name)
            onResponse.onSuccess(artistInfo.topAlbums)
        } catch (exception: Exception) {
            onResponse.onError(exception)
        }
    }

}