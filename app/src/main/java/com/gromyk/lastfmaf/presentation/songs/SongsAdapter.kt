package com.gromyk.lastfmaf.presentation.songs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gromyk.api.dtos.album.Track
import com.gromyk.lastfmaf.R
import com.gromyk.lastfmaf.helpers.TimeHelper
import com.gromyk.lastfmaf.presentation.base.BaseRecyclerAdapter

class SongsAdapter : BaseRecyclerAdapter<Track>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.song_item, parent, false))

    inner class ViewHolder(itemView: View) : BaseRecyclerAdapter.ViewHolder<Track>(itemView),
            View.OnClickListener {
        private val songNumberTextView by lazy { itemView.findViewById<TextView>(R.id.songNumberTextView) }
        private val songNameTextView by lazy { itemView.findViewById<TextView>(R.id.songNameTextView) }
        private val songArtistTextView by lazy { itemView.findViewById<TextView>(R.id.songArtistTextView) }
        private val songTimeTextView by lazy { itemView.findViewById<TextView>(R.id.songTimeTextView) }

        override fun bindView(item: Track) {
            itemView.setOnClickListener(this)
            songNumberTextView.text = (adapterPosition + 1).toString()
            songNameTextView.text = item.name
            songArtistTextView.text = item.artist.name
            songTimeTextView.text = TimeHelper.formatToMMSS(item.duration?.toInt())
        }

        override fun onClick(v: View?) {
        }
    }
}