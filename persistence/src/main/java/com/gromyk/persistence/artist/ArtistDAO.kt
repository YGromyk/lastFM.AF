package com.gromyk.persistence.artist

import androidx.room.Dao
import androidx.room.Query
import com.gromyk.persistence.base.BaseDAO

@Dao
interface ArtistDAO : BaseDAO<Artist> {
    @Query("delete from ${Artist.TABLE_NAME}")
    fun clearTable()

    @Query("select * from ${Artist.TABLE_NAME} where artist_name = :name")
    fun getArtistBy(name: String): Artist

    @Query("select * from ${Artist.TABLE_NAME}")
    fun getAllArtists(): List<Artist>
}