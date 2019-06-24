package com.gromyk.lastfmaf.domain.repository

import com.gromyk.api.Api
import com.gromyk.persistence.AlbumObject
import com.gromyk.persistence.AppDatabase
import com.gromyk.persistence.album.Album
import org.koin.core.KoinComponent
import org.koin.core.inject

class AlbumRepository : KoinComponent{
    val api: Api by inject()
    private val database: AppDatabase by inject()

    fun getLocalAlbums() = database.getAllAlbums()

    fun saveAlbum(album: AlbumObject) {
        database.saveAlbum(album)
    }

    fun removeAlbum(album: Album) {
        database.removeAlbum(album)
    }
}