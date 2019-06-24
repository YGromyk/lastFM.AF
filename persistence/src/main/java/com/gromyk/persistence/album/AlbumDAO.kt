package com.gromyk.persistence.album

import androidx.room.Dao
import androidx.room.Query
import com.gromyk.persistence.base.BaseDAO

@Dao
interface AlbumDAO : BaseDAO<Album> {
    @Query("select * from ${Album.TABLE_NAME}")
    fun getAllAlbums(): List<Album>

    @Query("delete from ${Album.TABLE_NAME} where url = :url")
    fun deleteBy(url: String)

    @Query("delete from ${Album.TABLE_NAME}")
    fun clearTable()
}