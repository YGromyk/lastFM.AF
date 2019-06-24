package com.gromyk.persistence.artist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = Artist.TABLE_NAME)
data class Artist(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "artist_id")
    var id: Int? = null,

    @SerializedName("bio")
    @ColumnInfo(name = "bio") var bio: Bio? = null,
    @SerializedName("image")
    @ColumnInfo(name = "image") var image: List<Image>? = null,
    @SerializedName("mbid")
    @ColumnInfo(name = "mbid") var mbid: String? = null,
    @SerializedName("name")
    @ColumnInfo(name = "name") var name: String? = null,
    @SerializedName("ontour")
    @ColumnInfo(name = "ontour") var ontour: String? = null,
    @SerializedName("similar")
    @ColumnInfo(name = "similar") var similar: Similar? = null,
    @SerializedName("stats")
    @ColumnInfo(name = "stats") var stats: Stats? = null,
    @SerializedName("streamable")
    @ColumnInfo(name = "streamable") var streamable: String? = null,
    @SerializedName("tags")
    @ColumnInfo(name = "tags") var tags: Tags? = null,
    @SerializedName("url")
    @ColumnInfo(name = "url") var url: String? = null
) {
    companion object {
        const val TABLE_NAME = "Artist"
    }
}