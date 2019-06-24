package com.gromyk.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gromyk.persistence.album.Album
import com.gromyk.persistence.album.Track
import com.gromyk.persistence.album.Wiki
import com.gromyk.persistence.artist.Artist
import com.gromyk.persistence.artist.Image
import com.gromyk.persistence.artist.Tag
import com.gromyk.persistence.converters.ImageListConverter
import com.gromyk.persistence.converters.TagListConverter
import com.gromyk.persistence.converters.TrackListConverter
import com.gromyk.persistence.converters.WikiConverter
import com.gromyk.persistence.dao.*
import timber.log.Timber

@Database(
    entities = [
        Album::class,
        Wiki::class,
        Image::class,
        Track::class,
        Tag::class,
        Artist::class
    ],
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
    protected abstract val albumDAO: AlbumDAO
    protected abstract val wikiDAO: WikiDAO
    protected abstract val imageDAO: ImageDAO
    protected abstract val albumObjectDAO: AlbumObjectDAO
    protected abstract val artistDAO: ArtistDAO
    protected abstract val tracksDAO: TracksDao
    protected abstract val tagsDAO: TagsDAO

    fun saveAlbum(album: AlbumObject) {
        val albumID = albumDAO.insert(album.album).firstOrNull() ?: -1L
        if (albumID == -1L) {
            Timber.e(Throwable("Album already saved"))
            return
        }
        album.artist?.let { artistDAO.insert(it) }
        album.wiki?.let {
            wikiDAO.insert(it.apply {
                it.albumId = albumId
            })
        }
        tracksDAO.insertArray(
            album.tracks
                .onEach { it.albumId = albumID }
                .toTypedArray()
        )
        tagsDAO.insertArray(album.tags.toTypedArray())
        imageDAO.insertArray(
            album.images
                .onEach { it.albumId = albumID }
                .toTypedArray()
        )
    }

    fun removeAlbum(album: Album) {
        albumDAO.remove(album)
    }

    fun getAllAlbums() = albumObjectDAO.getAlbums()
}

object DatabaseBuilder {
    fun buildDatabase(context: Context, dbName: String): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, dbName)
            .build()
}