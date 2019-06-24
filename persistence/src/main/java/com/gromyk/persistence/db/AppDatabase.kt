package com.gromyk.persistence.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gromyk.persistence.composedalbum.AlbumObject
import com.gromyk.persistence.album.Album
import com.gromyk.persistence.album.AlbumDAO
import com.gromyk.persistence.track.Track
import com.gromyk.persistence.wiki.Wiki
import com.gromyk.persistence.artist.Artist
import com.gromyk.persistence.artist.ArtistDAO
import com.gromyk.persistence.composedalbum.AlbumObjectDAO
import com.gromyk.persistence.image.Image
import com.gromyk.persistence.tag.Tag
import com.gromyk.persistence.image.ImageDAO
import com.gromyk.persistence.tag.TagsDAO
import com.gromyk.persistence.track.TracksDao
import com.gromyk.persistence.wiki.WikiDAO
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
abstract class AppDatabase : RoomDatabase() {
    protected abstract val albumDAO: AlbumDAO
    protected abstract val wikiDAO: WikiDAO
    protected abstract val imageDAO: ImageDAO
    protected abstract val albumObjectDAO: AlbumObjectDAO
    protected abstract val artistDAO: ArtistDAO
    protected abstract val tracksDAO: TracksDao
    protected abstract val tagsDAO: TagsDAO

    fun saveAlbum(album: AlbumObject): Boolean {
        val recordNumber = albumDAO.insert(album.album).firstOrNull() ?: -1L
        if (recordNumber == -1L) {
            Timber.e(Throwable("Album already saved"))
            return false
        }
        val albumId = album.album.albumId ?: recordNumber
        album.wiki.let {
            wikiDAO.insert(it.apply {
                it.albumId = albumId
            })
        }
        tracksDAO.insertArray(
            album.tracks
                .onEach { it.albumId = albumId }
                .toTypedArray()
        )
        tagsDAO.insertArray(
            album
                .tags
                .onEach { it.albumId = albumId }
                .toTypedArray()
        )
        imageDAO.insertArray(
            album.images
                .onEach { it.albumId = albumId }
                .toTypedArray()
        )

        return true
    }

    fun getAllArtists() = artistDAO.getAllArtists()

    fun getArtistBy(name: String) = artistDAO.getArtistBy(name)

    fun saveArtist(artist: Artist) = artistDAO.insert(artist)

    fun getAllAlbums(): List<AlbumObject> {
        return albumObjectDAO.getAlbums()
    }

    fun removeAlbums(albumObject: AlbumObject) {
        albumDAO.remove(albumObject.album)
        albumObject.images.forEach {
            imageDAO.remove(it)
        }
        albumObject.tracks.forEach {
            tracksDAO.remove(it)
        }
    }
}