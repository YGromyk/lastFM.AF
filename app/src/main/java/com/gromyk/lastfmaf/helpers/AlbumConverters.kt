package com.gromyk.lastfmaf.helpers

import com.gromyk.api.dtos.album.Album
import com.gromyk.api.dtos.album.Streamable
import com.gromyk.api.dtos.album.Track
import com.gromyk.api.dtos.album.Wiki
import com.gromyk.api.dtos.artist.*
import com.gromyk.api.dtos.topalbums.TopAlbum
import com.gromyk.lastfmaf.presentation.pojos.AlbumUI
import com.gromyk.persistence.artist.Links
import com.gromyk.persistence.artist.Tags

fun Album.toDBAlbum() = com.gromyk.persistence.album.Album(
    artist,
    ArrayList(image.map { it.toDBImage() } ?: emptyList()),
    listeners,
    mbid,
    name,
    playcount,
    ArrayList(tags.tag?.map { it.toDBTag() } ?: emptyList()),
    ArrayList(tracks.track?.map { it.toDBTrack() } ?: emptyList()),
    url,
    wiki?.toDBWiki()
)

fun TopAlbum.toDBAlbum() = com.gromyk.persistence.album.Album(
    artist?.name ?: "",
    ArrayList(image?.map { it.toDBImage() } ?: emptyList()),
    listeners,
    mbid,
    name ?: "",
    playcount,
    ArrayList(tags?.tag?.map { it.toDBTag() } ?: emptyList()),
    ArrayList(tracks?.track?.map { it.toDBTrack() } ?: emptyList()),
    url,
    wiki?.toDBWiki()
)

fun com.gromyk.persistence.album.Album.toUIAlbum() = AlbumUI(
    name,
    artist,
    image?.firstOrNull { it.text.isNotBlankAndEmpty() }?.text,
    false
)

fun Image.toDBImage() = com.gromyk.persistence.artist.Image(size, text)

fun Tag.toDBTag() = com.gromyk.persistence.artist.Tag(name, url)
fun Track.toDBTrack() =
    com.gromyk.persistence.album.Track(null, artist?.toDBArtist(), duration, name, streamable?.toDBStreamable(), url)

fun Streamable.toDBStreamable() = com.gromyk.persistence.album.Streamable(fullTrack, text)
fun Wiki.toDBWiki() = com.gromyk.persistence.album.Wiki(null, content, published, summary)

fun Artist.toDBArtist() = com.gromyk.persistence.artist.Artist(
    null,
    bio = bio?.toDBBio(),
    image = image?.map { it.toDBImage() },
    mbid = mbid,
    name = name,
    ontour = ontour,
    similar = similar?.toDBSimilar(),
    stats = stats?.toDBStats(),
    streamable = streamable,
    tags = Tags(tags?.tag?.map { it.toDBTag() } ?: emptyList()),
    url = url
)

fun Bio.toDBBio() = com.gromyk.persistence.artist.Bio(content, Links(links.link.toDBLink()), published, summary)
fun Link.toDBLink() = com.gromyk.persistence.artist.Link(href, rel, text)
fun Stats.toDBStats() = com.gromyk.persistence.artist.Stats(listeners, playcount)
fun Similar.toDBSimilar() = com.gromyk.persistence.artist.Similar(artist.map { it.toDBOtherArtist() })
fun OtherArtist.toDBOtherArtist() = com.gromyk.persistence.artist.OtherArtist(image.map { it.toDBImage() }, name, url)