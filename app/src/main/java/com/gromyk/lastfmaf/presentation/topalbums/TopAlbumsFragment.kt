package com.gromyk.lastfmaf.presentation.topalbums

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.gromyk.api.dtos.artist.Artist
import com.gromyk.lastfmaf.R
import com.gromyk.lastfmaf.helpers.loadPhoto
import com.gromyk.lastfmaf.presentation.FragmentParameters
import com.gromyk.lastfmaf.presentation.navigation.Navigator
import com.gromyk.lastfmaf.presentation.albumdetails.AlbumDetailsFragment
import com.gromyk.lastfmaf.presentation.albums.AlbumsAdapter
import com.gromyk.lastfmaf.presentation.base.BaseFragment
import com.gromyk.lastfmaf.presentation.pojos.AlbumUI
import com.gromyk.lastfmaf.presentation.pojos.getImageLink
import kotlinx.android.synthetic.main.artist_info.*
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
        getExtras()
        subscribeOnLiveDataVM()
        viewModel.fetchTopAlbumsBy()
        viewModel.fetchArtistInfo()
    }

    private fun subscribeOnLiveDataVM() {
        viewModel.apply {
            topAlbums.observe(this@TopAlbumsFragment, Observer(::onAlbumsLoaded))
            artistInfo.observe(this@TopAlbumsFragment, Observer(::onArtistLoaded))
            isResultReceived.observe(this@TopAlbumsFragment, Observer(::onTopAlbumsLoaded))
        }
    }


    private fun initView() {
        initToolbar()
        adapter = AlbumsAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        swipeRefreshLayout.setOnRefreshListener {
            if (viewModel.isResultReceived.value == true)
                viewModel.fetchTopAlbumsBy()
        }

        infoButton.setOnClickListener {
            moreInfoExpandableLayout?.apply {
                isExpanded = !isExpanded
            }
        }
    }

    private fun onAlbumsLoaded(albums: List<AlbumUI>) {
        swipeRefreshLayout.isRefreshing = false
        adapter.replaceItems(albums)
    }

    private fun onTopAlbumsLoaded(areTopAlbumsLoaded: Boolean) {
        if (areTopAlbumsLoaded) {
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onArtistLoaded(artist: Artist) {
        artistImageView.loadPhoto(artist.image?.getImageLink() ?: "")
        singerTextView.text = "Artist: ${artist.name}"
        artist.stats?.playcount?.let { playCountTextView.text = "Play count: $it" }
        artist.stats?.listeners?.let { listenersTextView.text = "Listeners: $it" }
        val wiki = artist.bio?.summary ?: ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            artistWikiTextView.text = Html.fromHtml(wiki, Html.FROM_HTML_MODE_COMPACT)
        } else {
            artistWikiTextView.text = Html.fromHtml(wiki)
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

    private fun getExtras() {
        arguments?.apply {
            getString(FragmentParameters.ARTIST_KEY)?.let {
                viewModel.searchedArtist = it
            }
        }
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