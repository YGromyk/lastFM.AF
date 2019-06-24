package com.gromyk.persistence.wiki

import androidx.room.Dao
import androidx.room.Query
import com.gromyk.persistence.base.BaseDAO

@Dao
interface WikiDAO : BaseDAO<Wiki> {
    @Query("select * from ${Wiki.TABLE_NAME} where ${Wiki.ALBUM} = :albumId")
    fun getWikiFor(albumId: Long): Wiki

    @Query("delete from ${Wiki.TABLE_NAME}")
    fun clearTable()
}