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
    var albumId: Int?,
    @ColumnInfo(name = "listeners")
    var listeners: String?,
    @ColumnInfo(name = "mbid")
    var mbid: String?,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "playcount")
    var playcount: String?,
    @ColumnInfo(name = "url")
    var url: String
) {
    constructor() : this(
        null,
        "", null, null,
        "", ""
    )

    companion object {
        const val TABLE_NAME = "Album"
        const val ALBUM_ID = "albumId"
    }
}