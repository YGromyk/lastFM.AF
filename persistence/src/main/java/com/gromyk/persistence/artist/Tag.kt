package com.gromyk.persistence.artist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Tag.TABLE_NAME)
class Tag(
    @PrimaryKey
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "url")
    var url: String
) {
    companion object {
        const val TABLE_NAME = "Tag"
    }
}