package com.gromyk.lastfmaf.presentation.albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.gromyk.lastfmaf.R
import com.gromyk.lastfmaf.helpers.loadPhoto
import com.gromyk.lastfmaf.presentation.base.BaseRecyclerAdapter
import com.gromyk.lastfmaf.presentation.pojos.AlbumUI
import java.lang.ref.WeakReference

class AlbumsAdapter(listener: OnSaveAlbum) : BaseRecyclerAdapter<AlbumUI>() {
    private val listener = WeakReference(listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false))

    @Suppress("Warnings")
    inner class ViewHolder(itemView: View) : BaseRecyclerAdapter.ViewHolder<AlbumUI>(itemView) {
        private val albumImageView: ImageView by lazy { itemView.findViewById<ImageView>(R.id.albumImageView) }
        private val saveAlbumButton: ImageButton by lazy { itemView.findViewById<ImageButton>(R.id.saveAlbumButton) }
        private val albumNameTextView: TextView by lazy { itemView.findViewById<TextView>(R.id.albumNameTextView) }
        private val albumSingerTextView: TextView by lazy { itemView.findViewById<TextView>(R.id.albumSingerTextView) }

        override fun bindView(item: AlbumUI) {
            item.imageLink?.let {
                albumImageView.loadPhoto(it)
            }
            albumNameTextView.text = item.name
            albumSingerTextView.text = item.artist
            saveAlbumButton.setOnClickListener { onSaveAlbum() }
        }

        private fun onSaveAlbum() {
            val album = items[adapterPosition]
            if (album.isSaved)
                listener.get()?.saveAlbum(album)
            else
                listener.get()?.removeAlbun(album)
        }
    }

    interface OnSaveAlbum {
        fun saveAlbum(albumUI: AlbumUI)
        fun removeAlbun(albumUI: AlbumUI)
    }
}