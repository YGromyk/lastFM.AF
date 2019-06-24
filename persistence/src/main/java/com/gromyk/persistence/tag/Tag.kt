package com.gromyk.persistence.tag

import androidx.room.*

@Entity(
    tableName = Tag.TABLE_NAME,
    indices = [Index(Tag.ALBUM)],
    primaryKeys = [Tag.ALBUM, "name"]
)
data class Tag
@Ignore constructor(
    @ColumnInfo(name = "tagForAlbum")
    var albumId: Long = -1,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "url")
    var url: String
) {
    constructor() : this(0, "", "")

    companion object {
        const val TABLE_NAME = "Tag"
        const val ALBUM = "tagForAlbum"
        const val TAG_ID = "tagId"
    }
}