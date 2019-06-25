package com.gromyk.lastfmaf.domain.repository

import com.gromyk.api.Api
import com.gromyk.lastfmaf.helpers.clearAndInsert
import com.gromyk.persistence.artist.Artist
import com.gromyk.persistence.composedalbum.AlbumObject
import com.gromyk.persistence.db.AppDatabase
import org.koin.core.KoinComponent
import org.koin.core.inject

class AlbumRepository : KoinComponent {
    val api: Api by inject()
    private val database: AppDatabase by inject()

    private val localAlbums = mutableListOf<AlbumObject>()


    fun getLocalAlbums(): List<AlbumObject> {
        val albums = database.getAllAlbums().toMutableList()
        localAlbums.clearAndInsert(albums)
        return localAlbums
    }

    // return id of artist
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
}