package com.gromyk.persistence.tag

import androidx.room.Dao
import androidx.room.Query
import com.gromyk.persistence.base.BaseDAO

@Dao
interface TagsDAO : BaseDAO<Tag> {
    @Query("delete from ${Tag.TABLE_NAME}")
    fun clearTable()
}