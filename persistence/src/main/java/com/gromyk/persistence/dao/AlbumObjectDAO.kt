package com.gromyk.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.gromyk.persistence.AlbumObject
import com.gromyk.persistence.album.Album

@Dao
interface AlbumObjectDAO {
    @Transaction
    @Query("SELECT * FROM ${Album.TABLE_NAME} WHERE ${Album.ALBUM_ID} = :albumId")
    fun getAlbumById(albumId: Long): AlbumObject

    @Transaction
    @Query("SELECT * FROM ${Album.TABLE_NAME}")
    fun getAlbums(): List<AlbumObject>
}