package com.gromyk.persistence.album


import androidx.room.ColumnInfo
import com.gromyk.persistence.artist.Image
import com.gromyk.persistence.artist.Tags

data class Album(
    @ColumnInfo(name = "artist")
    var artist: String,
    @ColumnInfo(name = "image")
    var image: List<Image>,
    @ColumnInfo(name = "listeners")
    var listeners: String,
    @ColumnInfo(name = "mbid")
    var mbid: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "playcount")
    var playcount: String,
    @ColumnInfo(name = "tags")
    var tags: Tags,
    @ColumnInfo(name = "tracks")
    var tracks: Tracks,
    @ColumnInfo(name = "url")
    var url: String,
    @ColumnInfo(name = "wiki")
    var wiki: Wiki?
)