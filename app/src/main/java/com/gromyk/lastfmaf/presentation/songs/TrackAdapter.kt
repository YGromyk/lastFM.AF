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

class TrackAdapter : BaseRecyclerAdapter<Track>() {

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
        }

        override fun onClick(v: View?) {
        }
    }
}