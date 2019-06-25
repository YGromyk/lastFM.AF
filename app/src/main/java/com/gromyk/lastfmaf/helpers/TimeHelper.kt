package com.gromyk.lastfmaf.helpers

object TimeHelper {
    fun formatToMMSS(timeInSeconds: Int): String {
        val time = StringBuilder()
        val minutes = timeInSeconds / 60
        val seconds = timeInSeconds % 60
        time.append(minutes)
        time.append(":")
        time.append(if (seconds < 10) "0$seconds" else seconds)
        return time.toString()
    }
}