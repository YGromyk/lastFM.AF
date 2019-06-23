package com.gromyk.persistence.artist

import androidx.room.ColumnInfo

data class Stats(
    @ColumnInfo(name = "listeners")
    var listeners: String,
    @ColumnInfo(name = "playcount")
    var playcount: String
)