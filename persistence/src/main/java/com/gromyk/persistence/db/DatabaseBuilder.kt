package com.gromyk.persistence.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object DatabaseBuilder {
    fun buildDatabase(context: Context, dbName: String) =
        Room.databaseBuilder(context, AppDatabase::class.java, dbName)
            .build()
}