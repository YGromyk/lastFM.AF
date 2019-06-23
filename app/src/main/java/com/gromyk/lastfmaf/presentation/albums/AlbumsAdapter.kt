package com.gromyk.lastfmaf.presentation.albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gromyk.lastfmaf.R
import com.gromyk.lastfmaf.helpers.loadPhoto
import com.gromyk.lastfmaf.presentation.base.BaseRecyclerAdapter
import com.gromyk.lastfmaf.presentation.pojos.AlbumUI
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.album_item.*
import java.lang.ref.WeakReference

class AlbumsAdapter(listener: OnSaveAlbum) : BaseRecyclerAdapter<AlbumUI>() {
    private val listener = WeakReference(listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false))

    inner class ViewHolder(override val containerView: View?) :
        BaseRecyclerAdapter.ViewHolder<AlbumUI>(containerView!!),
        View.OnClickListener,
        LayoutContainer {

        override fun bindView(item: AlbumUI) {
            itemView.setOnClickListener(this)
            item.imageLink?.let {
                albumImageView.loadPhoto(it)
            }
            albumNameTextView.text = item.name
            albumSingerTextView.text = item.artist
            saveAlbumButton.setOnClickListener { onSaveAlbum() }

            saveAlbumButton.setImageResource(
                if (item.isSaved) R.drawable.ic_delete_black_24dp
                else R.drawable.ic_save_black_24dp
            )
        }

        private fun onSaveAlbum() {
            val album = items[adapterPosition]
            if (album.isSaved)
                listener.get()?.saveAlbum(album)
            else
                listener.get()?.removeAlbum(album)
        }

        override fun onClick(v: View?) {
            listener.get()?.openAlbumDetails(items[adapterPosition])
        }
    }

    interface OnSaveAlbum {
        fun saveAlbum(albumUI: AlbumUI)
        fun removeAlbum(albumUI: AlbumUI)
        fun openAlbumDetails(albumUI: AlbumUI)
    }
}