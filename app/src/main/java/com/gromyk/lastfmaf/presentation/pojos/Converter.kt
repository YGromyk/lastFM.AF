package com.gromyk.lastfmaf.presentation.pojos


import com.gromyk.api.dtos.topalbums.TopAlbum
import com.gromyk.lastfmaf.helpers.ImageConfig
import com.gromyk.persistence.image.Image

fun TopAlbum.modelUI() = AlbumUI(
        name,
        artist?.name ?: throw Exception("LastFM API is bullshit"),
        image?.imageLinkAPI()
)

fun List<com.gromyk.api.dtos.artist.Image>.imageLinkAPI() =
        find { it.size == ImageConfig.imageSize }?.text ?: first { it.text.isNotBlank() }.text

fun List<Image>.imageLinkPersistence() =
        find { it.size == ImageConfig.imageSize }?.text ?: firstOrNull { it.text.isNotBlank() }?.text ?: ""