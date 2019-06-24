package com.gromyk.lastfmaf.domain.repository

import com.gromyk.api.Api
import com.gromyk.persistence.AppDatabase
import com.gromyk.persistence.album.Album
import org.koin.core.KoinComponent
import org.koin.core.inject

class AlbumRepository : KoinComponent{
    val api: Api by inject()
    val database: AppDatabase by inject()

    fun getLocalAlbums() = database.albumDAO.getAllAlbums()

    fun saveAlbum(album: Album) {
        database.albumDAO.insert(album)
    }

    fun removeAlbum(album: Album) {
        database.albumDAO.remove(album)
    }
}