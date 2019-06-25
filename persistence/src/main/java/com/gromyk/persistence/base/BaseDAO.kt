package com.gromyk.persistence.base

import androidx.room.*

@Dao
interface BaseDAO<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg obj: T): Array<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertArray(obj: Array<T>): Array<Long>

    @Delete
    fun remove(vararg obj: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg obj: T)
}