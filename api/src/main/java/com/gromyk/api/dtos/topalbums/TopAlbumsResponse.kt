package com.gromyk.api.dtos.topalbums

import com.google.gson.annotations.SerializedName
import com.gromyk.api.dtos.album.Tracks
import com.gromyk.api.dtos.album.Wiki
import com.gromyk.api.dtos.artist.Artist
import com.gromyk.api.dtos.artist.Image
import com.gromyk.api.dtos.artist.Tags

data class TopAlbumsResponse(
    @SerializedName("topalbums")
    var topAlbums: TopAlbums
)

data class TopAlbums(
    @SerializedName("album")
    var albums: List<TopAlbum>
)

data class TopAlbum(
    @SerializedName("artist")
    var artist: Artist?,
    @SerializedName("image")
    var image: List<Image>?,
    @SerializedName("listeners")
    var listeners: String?,
    @SerializedName("mbid")
    var mbid: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("playcount")
    var playcount: String?,
    @SerializedName("tags")
    var tags: Tags?,
    @SerializedName("tracks")
    var tracks: Tracks?,
    @SerializedName("url")
    var url: String,
    @SerializedName("wiki")
    var wiki: Wiki?
)