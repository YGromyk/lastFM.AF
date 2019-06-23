package com.gromyk.persistence.artist


import androidx.room.ColumnInfo
import com.gromyk.persistence.artist.OtherArtist

data class Similar(
    @ColumnInfo(name = "artist")
    var artist: List<OtherArtist>
)