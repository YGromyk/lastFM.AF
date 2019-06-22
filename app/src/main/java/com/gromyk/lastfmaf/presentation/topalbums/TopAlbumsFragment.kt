package com.gromyk.lastfmaf.presentation.topalbums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.gromyk.lastfmaf.R
import com.gromyk.lastfmaf.presentation.albums.AlbumsAdapter
import com.gromyk.lastfmaf.presentation.base.BaseFragment
import com.gromyk.lastfmaf.presentation.pojos.AlbumUI
import kotlinx.android.synthetic.main.progress_bar_layout.*
import kotlinx.android.synthetic.main.top_albums_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopAlbumsFragment : BaseFragment(), AlbumsAdapter.OnSaveAlbum {
    private val viewModel by viewModel<TopAlbumsViewModel>()
    private lateinit var adapter: AlbumsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.top_albums_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        subscribeOnLiveDataVM()
        viewModel.fetchTopAlbumsBy("\$uicideboy\$")
    }

    private fun subscribeOnLiveDataVM() {
        viewModel.apply {
            topAlbums.observe(this@TopAlbumsFragment, Observer(::onAlbumsLoaded))
            areTopAlbumsLoaded.observe(this@TopAlbumsFragment, Observer(::onTopAlbumsLoaded))
        }
    }


    private fun initView() {
        adapter = AlbumsAdapter(this)
        albumsRecyclerView.adapter = adapter
        albumsRecyclerView.layoutManager = GridLayoutManager(this.context, 2)
        swipeRefreshLayout.setOnRefreshListener {
            if (viewModel.areTopAlbumsLoaded.value == true)
                viewModel.fetchTopAlbumsBy(viewModel.searchedArtist ?: return@setOnRefreshListener)
        }
    }

    private fun onAlbumsLoaded(albums: List<AlbumUI>) {
        swipeRefreshLayout.isRefreshing = false
        adapter.replaceItems(albums)
    }

    private fun onTopAlbumsLoaded(areTopAlbumsLoaded: Boolean) {
        if (areTopAlbumsLoaded) {
            progressBar.visibility = View.GONE
            albumsRecyclerView.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.VISIBLE
            albumsRecyclerView.visibility = View.GONE
        }
    }

    override fun saveAlbum(albumUI: AlbumUI) {
        println("save")
    }

    override fun removeAlbun(albumUI: AlbumUI) {
        println("remove")
    }

    companion object {
        fun newInstance(parameters: Bundle? = null) = TopAlbumsFragment().apply {
            arguments = parameters
        }
    }
}