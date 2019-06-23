package com.gromyk.lastfmaf.presentation.topalbums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.gromyk.lastfmaf.R
import com.gromyk.lastfmaf.presentation.FragmentParameters
import com.gromyk.lastfmaf.presentation.Navigator
import com.gromyk.lastfmaf.presentation.albumdetails.AlbumDetailsFragment
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
//        viewModel.fetchTopAlbumsBy("\$uicideboy\$")
        viewModel.fetchTopAlbumsBy("Chere")

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

    override fun removeAlbum(albumUI: AlbumUI) {
        println("remove")
    }

    override fun openAlbumDetails(albumUI: AlbumUI) {
        val bundle = bundleOf(
                FragmentParameters.ALBUM_KEY to albumUI.name,
                FragmentParameters.ARTIST_KEY to albumUI.artist
        )
        activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.fragmentContainer, AlbumDetailsFragment.newInstance(bundle, navigator))
                ?.addToBackStack(AlbumDetailsFragment::class.java.simpleName)
                ?.commit()
    }

    companion object {
        fun newInstance(
                parameters: Bundle? = null,
                navigator: Navigator
        ) = TopAlbumsFragment().apply {
            arguments = parameters
            this.navigator = navigator
        }
    }
}