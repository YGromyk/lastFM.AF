package com.gromyk.lastfmaf.helpers

import com.gromyk.api.dtos.album.Album
import com.gromyk.api.dtos.album.Streamable
import com.gromyk.api.dtos.album.Track
import com.gromyk.api.dtos.album.Wiki
import com.gromyk.api.dtos.artist.*
import com.gromyk.api.dtos.topalbums.TopAlbum
import com.gromyk.lastfmaf.presentation.pojos.AlbumUI
import com.gromyk.lastfmaf.presentation.pojos.imageLinkAPI
import com.gromyk.lastfmaf.presentation.pojos.imageLinkPersistence
import com.gromyk.persistence.AlbumObject
import com.gromyk.persistence.artist.Links

fun Album.toDBAlbum() = com.gromyk.persistence.album.Album(
    null,
//    ArrayList(image.map { it.toDBImage() } ?: emptyList()),
    listeners,
    mbid,
    name,
    playcount,
    url
)


fun AlbumObject.toAlbumUI() = AlbumUI(
    album.name,
    artist?.name,
    images.imageLinkPersistence(),
    true
)

fun TopAlbum.toAlbumUI() = AlbumUI(
    name,
    artist?.name,
    image?.imageLinkAPI(),
    false
)

fun TopAlbum.toDBAlbum() = com.gromyk.persistence.album.Album(
    null,
//    ArrayList(image?.map { it.toDBImage() } ?: emptyList()),
    listeners,
    mbid,
    name ?: "",
    playcount,
    url
)

fun Track.toDBTrack() = com.gromyk.persistence.album.Track(
    -1L,
    duration,
    name,
    url
)

fun Image.toDBImage() = com.gromyk.persistence.artist.Image(null, size, text)

fun Tag.toDBTag() = com.gromyk.persistence.artist.Tag(null, name, url)


fun Streamable.toDBStreamable() = com.gromyk.persistence.album.Streamable(fullTrack, text)
fun Wiki.toDBWiki() = com.gromyk.persistence.album.Wiki(null, content, published, summary)

fun Artist.toDBArtist() = com.gromyk.persistence.artist.Artist(
    null,
    mbid = mbid,
    name = name ?: "",
    ontour = ontour,
    url = url ?: ""
)

fun Bio.toDBBio() = com.gromyk.persistence.artist.Bio(content, Links(links.link.toDBLink()), published, summary)
fun Link.toDBLink() = com.gromyk.persistence.artist.Link(href, rel, text)
fun Stats.toDBStats() = com.gromyk.persistence.artist.Stats(listeners, playcount)
fun Similar.toDBSimilar() = com.gromyk.persistence.artist.Similar(artist.map { it.toDBOtherArtist() })
fun OtherArtist.toDBOtherArtist() = com.gromyk.persistence.artist.OtherArtist(image.map { it.toDBImage() }, name, url)
