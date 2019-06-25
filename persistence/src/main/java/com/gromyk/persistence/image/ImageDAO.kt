package com.gromyk.persistence.image

import androidx.room.Dao
import androidx.room.Query
import com.gromyk.persistence.base.BaseDAO

@Dao
interface ImageDAO : BaseDAO<Image> {
    @Query("select * from ${Image.TABLE_NAME} where ${Image.ALBUM} = :albumId")
    fun getImagesBy(albumId: Int): List<Image>

    @Query("delete from ${Image.TABLE_NAME}")
    fun clearTable()
}