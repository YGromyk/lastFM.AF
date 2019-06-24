package com.gromyk.persistence

import androidx.room.Embedded
import androidx.room.Relation
import com.gromyk.persistence.album.Album
import com.gromyk.persistence.album.Track
import com.gromyk.persistence.album.Wiki
import com.gromyk.persistence.artist.Artist
import com.gromyk.persistence.artist.Image
import com.gromyk.persistence.artist.Tag

class AlbumObject(
    @Embedded
    var album: Album,
    @Embedded
    var wiki: Wiki?,
    @Relation(
        entity = Image::class,
        entityColumn = Image.ALBUM,
        parentColumn = Album.ALBUM_ID
    )
    var images: List<Image>,
    @Relation(
        entity = Track::class,
        entityColumn = Track.ALBUM,
        parentColumn = Album.ALBUM_ID
    )
    var tracks: List<Track>,

    @Relation(
        entity = Tag::class,
        entityColumn = Tag.ALBUM,
        parentColumn = Album.ALBUM_ID
    )
    var tags: List<Tag>,

    @Embedded
    var artist: Artist?
) {
    constructor() : this(Album(), Wiki(), emptyList(), emptyList(), emptyList(), null)
}