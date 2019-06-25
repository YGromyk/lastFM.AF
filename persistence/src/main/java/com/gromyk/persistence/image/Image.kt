package com.gromyk.persistence.image

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = Image.TABLE_NAME,
    indices = [Index(Image.ALBUM, "size")],
    primaryKeys = [Image.ALBUM, "size"]
)
data class Image(
    @ColumnInfo(name = ALBUM)
    var albumId: Long = -1L,
    @SerializedName("size")
    var size: String,
    @SerializedName("text")
    var text: String
) {
    constructor() : this(-1L, "", "")

    companion object {
        const val TABLE_NAME = "Images"
        const val ALBUM = "imageForAlbum"
    }
}