package com.gromyk.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.gromyk.persistence.album.Album
import com.gromyk.persistence.album.Wiki

@Dao
interface WikiDAO : BaseDAO<Wiki> {
    @Query("select * from ${Wiki.TABLE_NAME} where ${Wiki.ALBUM} = :albumId")
    fun getWikiFor(albumId: Int): Wiki
}