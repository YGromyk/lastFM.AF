package com.gromyk.persistence.dao

import androidx.room.Dao
import com.gromyk.persistence.artist.Tag

@Dao
interface TagsDAO : BaseDAO<Tag>