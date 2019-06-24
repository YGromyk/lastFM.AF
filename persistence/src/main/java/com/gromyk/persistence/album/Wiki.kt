package com.gromyk.persistence.album

import com.google.gson.annotations.SerializedName

class Wiki(
    @SerializedName("wikiId")
    var columnId: Int? = null,
    @SerializedName("content")
    var content: String,
    @SerializedName("published")
    var published: String,
    @SerializedName("summary")
    var summary: String
) {
    companion object {
        const val TABLE_NAME = "Wiki"
    }
}