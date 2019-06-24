package com.gromyk.persistence.dao

import androidx.room.Dao
import com.gromyk.persistence.album.Track

@Dao
interface TracksDao : BaseDAO<Track>