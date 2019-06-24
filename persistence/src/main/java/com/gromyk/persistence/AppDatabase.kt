package com.gromyk.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gromyk.persistence.album.Album
import com.gromyk.persistence.converters.ImageListConverter
import com.gromyk.persistence.converters.TagListConverter
import com.gromyk.persistence.converters.TrackListConverter
import com.gromyk.persistence.converters.WikiConverter
import com.gromyk.persistence.dao.AlbumDAO

@Database(
    entities = [Album::class],
    version = DatabaseConstants.version1,
    exportSchema = false
)
@TypeConverters(
    TrackListConverter::class,
    ImageListConverter::class,
    TagListConverter::class,
    WikiConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract val albumDAO: AlbumDAO
}

object DatabaseBuilder {
    fun buildDatabase(context: Context, dbName: String): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, dbName)
            .build()
}