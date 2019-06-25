package com.gromyk.persistence.wiki

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = Wiki.TABLE_NAME)
class Wiki
@Ignore constructor(
    @PrimaryKey
    @ColumnInfo(name = ALBUM)
    var albumId: Long? = null,
    @ColumnInfo(name = "content")
    var content: String?,
    @ColumnInfo(name = "published")
    var published: String?,
    @ColumnInfo(name = "summary")
    var summary: String?
) {
    constructor() : this(
        null,
        null,
        "",
        ""
    )

    companion object {
        const val TABLE_NAME = "Wiki"
        const val ALBUM = "wikiForAlbum"
    }
}