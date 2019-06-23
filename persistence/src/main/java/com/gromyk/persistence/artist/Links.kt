package com.gromyk.persistence.artist

import androidx.room.ColumnInfo

data class Links(
    @ColumnInfo(name = "link")
    var link: Link
)