package com.gromyk.persistence.artist

import androidx.room.ColumnInfo
import androidx.room.Embedded


data class ArtistInfo(
    @Embedded
    var artist: Artist,
    @Embedded
    var artistStats: Stats,
    var artistTags: List<Tag>,
    var artistBio: Bio

)