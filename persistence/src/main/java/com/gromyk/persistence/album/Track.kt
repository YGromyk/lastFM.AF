package com.gromyk.persistence.album


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index

@Entity(
    tableName = Track.TABLE_NAME,
    indices = [Index(Track.ALBUM)],
    primaryKeys = [ Track.TRACK_NAME, "url"]
)
class Track
@Ignore constructor(
    @ColumnInfo(name = ALBUM)
    var albumId: Long = 0,
    @ColumnInfo(name = "duration")
    var duration: String,
    @ColumnInfo(name = TRACK_NAME)
    var name: String,
    @ColumnInfo(name = "url")
    var url: String
) {
    constructor() : this(-1, "", "", "")

    companion object {
        const val TABLE_NAME = "Tracks"
        const val TRACK_NAME = "name"
        const val ALBUM = "parentAlbum"
    }
}