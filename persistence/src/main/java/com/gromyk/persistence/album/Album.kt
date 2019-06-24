package com.gromyk.persistence.album


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverters
import com.gromyk.persistence.artist.Image
import com.gromyk.persistence.artist.Tag
import com.gromyk.persistence.converters.ImageListConverter
import com.gromyk.persistence.converters.TagListConverter
import com.gromyk.persistence.converters.TrackListConverter
import com.gromyk.persistence.converters.WikiConverter

@Entity(
    tableName = Album.TABLE_NAME,
    primaryKeys = ["artist", "name"]
)
@TypeConverters(
    ImageListConverter::class,
    TagListConverter::class,
    TrackListConverter::class,
    WikiConverter::class
)
data class Album(
    @ColumnInfo(name = "artist")
    var artist: String,
    @ColumnInfo(name = "image")
    var image: ArrayList<Image>?,
    @ColumnInfo(name = "listeners")
    var listeners: String?,
    @ColumnInfo(name = "mbid")
    var mbid: String?,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "playcount")
    var playcount: String?,
    @ColumnInfo(name = "tags")
    var tags: ArrayList<Tag>?,
    @ColumnInfo(name = "tracks")
    var tracks: ArrayList<Track>?,
    @ColumnInfo(name = "url")
    var url: String?,
    @ColumnInfo(name = "wiki")
    var wiki: Wiki?
) {
    constructor() : this(
        "", null, null, null,
        "", null, null, null, null, null
    )

    companion object {
        const val TABLE_NAME = "Album"
    }
}