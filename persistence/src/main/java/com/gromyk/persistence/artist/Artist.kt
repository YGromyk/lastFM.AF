package com.gromyk.persistence.artist

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = Artist.TABLE_NAME,
    primaryKeys = ["artist_name", "artist_url"]
)
data class Artist(
    @ColumnInfo(name = "artist_id")
    var id: Long = -1L,
    @SerializedName("mbid")
    @ColumnInfo(name = "artist_mbid")
    var mbid: String? = null,
    @SerializedName("name")
    @ColumnInfo(name = "artist_name")
    var name: String,
    @SerializedName("ontour")
    @ColumnInfo(name = "ontour")
    var ontour: String? = null,
    @SerializedName("url")
    @ColumnInfo(name = "artist_url")
    var url: String
) {
    constructor() : this(
        -1L,
        null,
        "",
        null,
        ""
    )

    companion object {
        const val TABLE_NAME = "Artist"
    }
}