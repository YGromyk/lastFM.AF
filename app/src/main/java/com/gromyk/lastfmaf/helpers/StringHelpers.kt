package com.gromyk.lastfmaf.helpers

fun String?.isNotBlankAndEmpty(): Boolean {
    return this?.isNotEmpty() == true && this.isNotBlank()
}