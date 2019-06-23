package com.gromyk.persistence.artist

import androidx.room.ColumnInfo

data class Artist(
    @ColumnInfo(name = "bio")
    var bio: Bio? = null,
    @ColumnInfo(name = "image")
    var image: List<Image>? = null,
    @ColumnInfo(name = "mbid")
    var mbid: String? = null,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "ontour")
    var ontour: String? = null,
    @ColumnInfo(name = "similar")
    var similar: Similar? = null,
    @ColumnInfo(name = "stats")
    var stats: Stats? = null,
    @ColumnInfo(name = "streamable")
    var streamable: String? = null,
    @ColumnInfo(name = "tags")
    var tags: Tags? = null,
    @ColumnInfo(name = "url")
    var url: String? = null
)