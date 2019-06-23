package com.gromyk.persistence.artist

import androidx.room.ColumnInfo


data class ArtistInfo(
    @ColumnInfo(name = "artist")
    var artist: Artist
)