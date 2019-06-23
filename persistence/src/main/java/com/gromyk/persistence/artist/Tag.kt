package com.gromyk.persistence.artist

import androidx.room.ColumnInfo

data class Tag(
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "url")
    var url: String
)