package com.gromyk.lastfmaf.presentation.pojos

import com.gromyk.api.dtos.album.Album
import com.gromyk.api.dtos.album.Track
import com.gromyk.api.dtos.album.Wiki
import com.gromyk.api.dtos.artist.Artist
import com.gromyk.api.dtos.artist.Image
import com.gromyk.api.dtos.artist.Tag
import com.gromyk.api.dtos.topalbums.TopAlbum
import com.gromyk.persistence.composedalbum.AlbumObject

fun Album.toDBAlbum() = com.gromyk.persistence.album.Album(
    null,
    listeners,
    mbid,
    name,
    playcount,
    url,
    -1L
)


fun AlbumObject.toAlbumUI(artistName: String) = AlbumUI(
    album?.name,
    artistName,
    images.imageLinkPersistence(),
    true
)

fun TopAlbum.toAlbumUI() = AlbumUI(
    name,
    artist?.name,
    image?.imageLinkAPI(),
    false
)

fun Track.toDBTrack() = com.gromyk.persistence.track.Track(
    -1L,
    duration,
    name,
    url
).apply {
    artistName = artist?.name
}

fun Image.toDBImage() = com.gromyk.persistence.image.Image(-1L, size, text)

fun Tag.toDBTag() = com.gromyk.persistence.tag.Tag(-1L, name, url)

fun Wiki.toDBWiki() =
    com.gromyk.persistence.wiki.Wiki(null, content, published, summary)


fun Artist.toDBArtist() = com.gromyk.persistence.artist.Artist(
    -1L,
    mbid = mbid,
    name = name ?: "",
    ontour = ontour,
    url = url ?: ""
)

fun Album.toAlbumObject() = AlbumObject(
    toDBAlbum(),
    wiki?.toDBWiki() ?: com.gromyk.persistence.wiki.Wiki(),
    image.map { it.toDBImage() },
    tracks.track?.map { it.toDBTrack() } ?: emptyList(),
    tags.tag?.map { it.toDBTag() } ?: emptyList()
)