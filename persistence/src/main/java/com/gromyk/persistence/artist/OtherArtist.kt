package com.gromyk.persistence.artist


import androidx.room.ColumnInfo

/**
 * Represents other artist with few details
 */
data class OtherArtist(
    @ColumnInfo(name = "image")
    var image: List<Image>,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "url")
    var url: String
)