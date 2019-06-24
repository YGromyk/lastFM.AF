package com.gromyk.persistence.dao

import androidx.room.*

@Dao
interface BaseDAO<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj: T)

    @Delete
    fun remove(vararg obj: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg obj: T)

}