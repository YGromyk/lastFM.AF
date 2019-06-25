package com.gromyk.lastfmaf.helpers

import android.widget.ImageView
import com.gromyk.lastfmaf.R
import com.squareup.picasso.Picasso

object ImageConfig {
    const val imageSize = "extralarge"
}

fun ImageView.loadPhoto(url: String) {
    if (url.isNotBlank())
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.music_placeholder)
            .into(this)
    else
        setImageResource(R.drawable.music_placeholder)
}