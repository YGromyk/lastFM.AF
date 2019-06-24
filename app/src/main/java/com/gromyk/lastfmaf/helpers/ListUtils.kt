package com.gromyk.lastfmaf.helpers

fun<T> MutableList<T>.clearAndInsert(collection: Collection<T>) {
    clear()
    addAll(collection)
}