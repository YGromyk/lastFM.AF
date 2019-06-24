package com.gromyk.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.gromyk.persistence.album.Album
import com.gromyk.persistence.artist.Image

@Dao
interface ImageDAO : BaseDAO<Image> {
    @Query("select * from ${Image.TABLE_NAME} where ${Image.ALBUM} = :albumId")
    fun getImagesBy(albumId: Int): List<Image>
}