package com.gromyk.lastfmaf.presentation.songs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gromyk.lastfmaf.R
import com.gromyk.lastfmaf.helpers.TimeHelper
import com.gromyk.lastfmaf.presentation.base.BaseRecyclerAdapter
import com.gromyk.persistence.track.Track
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.song_item.*
import java.lang.ref.WeakReference

class TrackAdapter(listener: OnYouTubeClicked) : BaseRecyclerAdapter<Track>() {
    val onYouTubeClickListener = WeakReference(listener)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.song_item, parent, false))

    inner class ViewHolder(override val containerView: View?) :
        BaseRecyclerAdapter.ViewHolder<Track>(containerView!!),
        View.OnClickListener,
        LayoutContainer {

        override fun bindView(item: Track) {
            itemView.setOnClickListener(this)
            songNumberTextView.text = (adapterPosition + 1).toString()
            songNameTextView.text = item.name
            songArtistTextView.text = item.artistName ?: ""
            songTimeTextView.text = TimeHelper.formatToMMSS(item.duration.toInt())
            youtubeImageView.setOnClickListener {
                onYouTubeClicked(item)
            }
        }

        override fun onClick(v: View?) = Unit

        private fun onYouTubeClicked(item: Track) {
            onYouTubeClickListener.get()?.openYouTube(item)
        }
    }

    interface OnYouTubeClicked {
        fun openYouTube(track: Track)
    }
}