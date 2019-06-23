package com.gromyk.persistence.artist

import androidx.room.ColumnInfo

data class Tags(
    @ColumnInfo(name = "tag")
    var tag: List<Tag>
)