package com.gromyk.lastfmaf.presentation.pojos


import com.gromyk.api.dtos.topalbums.TopAlbum
import com.gromyk.lastfmaf.helpers.ImageConfig

fun TopAlbum.modelUI() = AlbumUI(
    name,
    artist.name ?: throw Exception("LastFM API is bullshit"),
    image.find { it.size == ImageConfig.imageSize }?.text ?: image.first { it.text.isNotBlank() }.text
)