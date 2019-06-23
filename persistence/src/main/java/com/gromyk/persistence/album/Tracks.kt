package com.gromyk.persistence.album


import androidx.room.ColumnInfo
import com.gromyk.persistence.album.Track

data class Tracks(
    @ColumnInfo(name = "track")
    var track: List<Track>
)