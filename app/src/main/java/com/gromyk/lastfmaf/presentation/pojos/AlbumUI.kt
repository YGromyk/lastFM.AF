package com.gromyk.lastfmaf.presentation.pojos

data class AlbumUI (
    var name: String,
    var artist: String,
    var imageLink: String?,
    var isSaved: Boolean = false
)
