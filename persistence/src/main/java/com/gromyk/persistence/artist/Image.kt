package com.gromyk.persistence.artist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = Image.TABLE_NAME,
    indices = [Index(Image.ALBUM, "size")]
)
data class Image(
    @PrimaryKey
    @ColumnInfo(name = ALBUM)
    var albumId: Long? = null,
    @SerializedName("size")
    var size: String,
    @SerializedName("text")
    var text: String
) {
    constructor() : this(null, "", "")

    companion object {
        const val TABLE_NAME = "Images"
        const val ALBUM = "imageForAlbum"
    }
}