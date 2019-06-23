package com.gromyk.persistence.album


import androidx.room.ColumnInfo

data class Attr(
    @ColumnInfo(name = "rank")
    var rank: String
)