package com.gromyk.persistence.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.gromyk.persistence.album.Track
import com.gromyk.persistence.album.Wiki
import com.gromyk.persistence.artist.Image
import com.gromyk.persistence.artist.Tag
import java.util.*
import kotlin.collections.ArrayList


open class BaseListConverter<T>{
    private var gson = Gson()

    @TypeConverter
    fun stringToList(data: String?): ArrayList<T>? {
        if (data == null) {
            return ArrayList()
        }
        val listType = object : TypeToken<List<T>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: ArrayList<T>?): String {
        return gson.toJson(someObjects)
    }
}

open class BaseObjectConverter<T>{
    private var gson = Gson()

    @TypeConverter
    fun stringToObject(data: String?): T {
        val type = object : TypeToken<List<T>>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun objectToSTring(obj: T): String {
        return gson.toJson(obj)
    }
}

class ImageListConverter: BaseListConverter<Image>()
class TrackListConverter: BaseListConverter<Track>()
class TagListConverter: BaseListConverter<Tag>()

class WikiConverter: BaseObjectConverter<Wiki>()