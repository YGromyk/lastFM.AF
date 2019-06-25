package com.gromyk.persistence.album


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

@Entity(
    tableName = Album.TABLE_NAME,
    primaryKeys = ["url"]
)
class Album
@Ignore constructor(
    var albumId: Long?,
    @ColumnInfo(name = "listeners")
    var listeners: String?,
    @ColumnInfo(name = "mbid")
    var mbid: String?,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "playcount")
    var playcount: String?,
    @ColumnInfo(name = "url")
    var url: String,
    @ColumnInfo(name = "artistId")
    var artistId: Long
) {
    constructor() : this(
        null,
        "",
        null,
        null,
        "",
        "",
        -1L
    )

    companion object {
        const val TABLE_NAME = "Album"
        const val ALBUM_ID = "albumId"
    }
}