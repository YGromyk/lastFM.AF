package com.gromyk.persistence.composedalbum

import androidx.room.Embedded
import androidx.room.Relation
import com.gromyk.persistence.album.Album
import com.gromyk.persistence.image.Image
import com.gromyk.persistence.tag.Tag
import com.gromyk.persistence.track.Track
import com.gromyk.persistence.wiki.Wiki

class AlbumObject(
    @Embedded
    var album: Album?,
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
    var tags: List<Tag>
) {
    @Suppress("unused")
    constructor() : this(
        Album(),
        Wiki(),
        emptyList(),
        emptyList(),
        emptyList()
    )
}