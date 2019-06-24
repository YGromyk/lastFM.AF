package com.gromyk.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.gromyk.persistence.album.Album

@Dao
interface AlbumDAO : BaseDAO<Album> {
    @Query("select * from ${Album.TABLE_NAME}")
    fun getAllAlbums(): List<Album>
}