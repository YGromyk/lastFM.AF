package com.gromyk.persistence.dao

import androidx.room.Dao
import com.gromyk.persistence.artist.Artist

@Dao
interface ArtistDAO: BaseDAO<Artist>