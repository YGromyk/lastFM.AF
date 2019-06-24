package com.gromyk.persistence.track

import androidx.room.Dao
import androidx.room.Query
import com.gromyk.persistence.base.BaseDAO

@Dao
interface TracksDao : BaseDAO<Track> {
    @Query("delete from ${Track.TABLE_NAME}")
    fun clearTable()
}