package com.gromyk.persistence.artist

import androidx.room.*
import com.gromyk.persistence.album.Album

@Entity(
    tableName = Tag.TABLE_NAME,
/*    foreignKeys = [
        ForeignKey(
            entity = Tag::class,
            childColumns = [Album.ALBUM_ID],
            parentColumns= [Tag.ALBUM],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],*/
    indices = [Index(Tag.ALBUM)]
)
data class Tag
@Ignore constructor(
    @ColumnInfo(name = "tagForAlbum")
    var albumId: Int? = null,
    @PrimaryKey
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