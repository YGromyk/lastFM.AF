package com.gromyk.lastfmaf.presentation.pojos


import com.gromyk.api.dtos.artist.Image
import com.gromyk.api.dtos.topalbums.TopAlbum
import com.gromyk.lastfmaf.helpers.ImageConfig

fun TopAlbum.modelUI() = AlbumUI(
        name,
        artist?.name ?: throw Exception("LastFM API is bullshit"),
        image?.getImageLink()
)

fun List<Image>.getImageLink() =
        find { it.size == ImageConfig.imageSize }?.text ?: first { it.text.isNotBlank() }.text