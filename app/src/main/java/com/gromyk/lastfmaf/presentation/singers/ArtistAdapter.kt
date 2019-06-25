package com.gromyk.lastfmaf.presentation.singers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gromyk.api.dtos.artist.Artist
import com.gromyk.lastfmaf.R
import com.gromyk.lastfmaf.helpers.loadPhoto
import com.gromyk.lastfmaf.presentation.base.BaseRecyclerAdapter
import com.gromyk.lastfmaf.presentation.pojos.imageLinkAPI
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.artist_item.*
import java.lang.ref.WeakReference

class ArtistAdapter(listener: OnArtistSelected) : BaseRecyclerAdapter<Artist>() {
    private val listener = WeakReference(listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.artist_item, parent, false))

    inner class ViewHolder(override val containerView: View?) : BaseRecyclerAdapter.ViewHolder<Artist>(containerView!!),
            View.OnClickListener,
            LayoutContainer {

        @Suppress("warnings")
        override fun bindView(item: Artist) {
            itemView.setOnClickListener(this)
            item.image?.imageLinkAPI()?.let {
                artistImageView.loadPhoto(it)
            }
            singerTextView.text = item.name
            singerListenersTextView.text = "${item.listeners ?: 0} listeners"
        }

        override fun onClick(v: View?) {
            val artist = items[adapterPosition]
            listener.get()?.onArtistSelected(artist)
        }
    }

    interface OnArtistSelected {
        fun onArtistSelected(artist: Artist)
    }
}